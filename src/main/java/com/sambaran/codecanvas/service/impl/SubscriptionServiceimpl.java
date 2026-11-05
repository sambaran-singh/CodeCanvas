package com.sambaran.codecanvas.service.impl;

import com.sambaran.codecanvas.dto.subscription.SubscriptionResponse;
import com.sambaran.codecanvas.entity.Plan;
import com.sambaran.codecanvas.entity.Subscription;
import com.sambaran.codecanvas.entity.User;
import com.sambaran.codecanvas.enums.SubscriptionStatus;
import com.sambaran.codecanvas.error.ResourceNotFoundException;
import com.sambaran.codecanvas.mapper.SubscriptionMapper;
import com.sambaran.codecanvas.repository.PlanRepository;
import com.sambaran.codecanvas.repository.ProjectMemberRepository;
import com.sambaran.codecanvas.repository.SubscriptionRepository;
import com.sambaran.codecanvas.repository.UserRepository;
import com.sambaran.codecanvas.security.AuthUtil;
import com.sambaran.codecanvas.service.SubscriptionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;

/**
 * @author Sambaran
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionServiceimpl implements SubscriptionService {
    private final ProjectMemberRepository projectMemberRepository;

    private final AuthUtil authUtil;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserRepository userRepository;
    private final PlanRepository planRepository;

    private final Integer FREE_TIER_PROJECTS_ALLOWED = 1;
    @Override
    public SubscriptionResponse getCurrentSubscription() {
        Long userId = authUtil.getCurrentUserId();

        var currentSubscription = subscriptionRepository
                .findByUserIdAndStatusIn(userId, Set.of(
                   SubscriptionStatus.ACTIVE, SubscriptionStatus.PAST_DUE,
                   SubscriptionStatus.TRIALING))
                .orElse(new Subscription());

        return subscriptionMapper.toSubscriptionResponse(currentSubscription);
    }

    @Override
    public void activateSubscription(Long userId, Long planId, String gatewaySubscriptionId, String customerId) {
        boolean exists = subscriptionRepository.existsByStripeSubscriptionId(gatewaySubscriptionId);
        if(exists) return;

        User user = getUser(userId);
        Plan plan = getPlan(planId);

        Subscription subscription = Subscription.builder()
                .user(user)
                .plan(plan)
                .stripeSubscriptionId(gatewaySubscriptionId)
                .status(SubscriptionStatus.INCOMPLETE)
                .build();

        subscriptionRepository.save(subscription);
    }




    @Override
    @Transactional
    public void updateSubscription(String gatewaySubscriptionId, SubscriptionStatus status, Instant periodStart, Instant periodEnd, Boolean cancelAtPeriodEnd, Long planId) {
        Subscription subscription = getSubscription(gatewaySubscriptionId);

        boolean hasSubscriptionUpdated = false;

        if(status != null && status != subscription.getStatus()) {
            subscription.setStatus(status);
            hasSubscriptionUpdated = true;
        }

        if(periodStart != null && !periodStart.equals(subscription.getCurrentPeriodStart())) {
            subscription.setCurrentPeriodStart(periodStart);
            hasSubscriptionUpdated = true;
        }

        if(periodEnd != null && !periodEnd.equals(subscription.getCurrentPeriodEnd())) {
            subscription.setCurrentPeriodEnd(periodEnd);
            hasSubscriptionUpdated = true;
        }

        if(cancelAtPeriodEnd != null && cancelAtPeriodEnd != subscription.getCancelAtPeriodEnd()) {
            subscription.setCancelAtPeriodEnd(cancelAtPeriodEnd);
            hasSubscriptionUpdated = true;
        }

        if(planId != null && !planId.equals(subscription.getPlan().getId())) {
            Plan plan = getPlan(planId);
            subscription.setPlan(plan);
            hasSubscriptionUpdated = true;
        }

        if(hasSubscriptionUpdated){
            log.debug("Subscription is updated, gatewaySubscriptionId: {}", gatewaySubscriptionId);
            subscriptionRepository.save(subscription);
        }


    }

    @Override
    public void cancelSubscription(String gatewaySubscriptionId) {
        Subscription subscription = getSubscription(gatewaySubscriptionId);
        subscription.setStatus(SubscriptionStatus.CANCELED);
        subscriptionRepository.save(subscription);

    }

    @Override
    public void renewSubscriptionPeriod(String gatewaySubscriptionId, Instant periodStart, Instant periodEnd) {
        Subscription subscription = getSubscription(gatewaySubscriptionId);

        Instant newStart = periodStart != null? periodStart:subscription.getCurrentPeriodEnd();

        subscription.setCurrentPeriodStart(newStart);
        subscription.setCurrentPeriodEnd(periodEnd);

        if(subscription.getStatus() == SubscriptionStatus.PAST_DUE || subscription.getStatus() == SubscriptionStatus.INCOMPLETE)
            subscription.setStatus(SubscriptionStatus.ACTIVE);

        subscriptionRepository.save(subscription);
    }


    @Override
    public void markSubscriptionPastDue(String gatewaySubscriptionId) {
        Subscription subscription = getSubscription(gatewaySubscriptionId);
        if(subscription.getStatus() == SubscriptionStatus.PAST_DUE) {
            log.debug("Subscription is already past due , gatewaySubscriptionId: {}", gatewaySubscriptionId);
            return;
        }
        subscription.setStatus(SubscriptionStatus.PAST_DUE);
        subscriptionRepository.save(subscription);

        //Notify the user with email
    }

    @Override
    public boolean canCreateNewProject() {
        Long userId = authUtil.getCurrentUserId();
        SubscriptionResponse currentSubscription = getCurrentSubscription();

        int countOfOwnedProjects = projectMemberRepository.countProjectOwnedByUser(userId);
        if(currentSubscription.plan() == null){
            return countOfOwnedProjects < FREE_TIER_PROJECTS_ALLOWED;
        }

        return countOfOwnedProjects < currentSubscription.plan().maxProject();
    }


    //Utility Methods
    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("user", userId.toString())
        );
    }
    private Plan getPlan(Long planId) {
        return planRepository.findById(planId).orElseThrow(() -> new ResourceNotFoundException("plan", planId.toString()));
    }

    private Subscription getSubscription(String subId) {
        return subscriptionRepository.findByStripeSubscriptionId(subId).orElseThrow(() -> new ResourceNotFoundException("Subscription", subId));
    }


}
