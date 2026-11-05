package com.sambaran.codecanvas.service.impl;

import com.sambaran.codecanvas.dto.subscription.CheckoutRequest;
import com.sambaran.codecanvas.dto.subscription.CheckoutResponse;
import com.sambaran.codecanvas.dto.subscription.PortalResponse;
import com.sambaran.codecanvas.dto.subscription.SubscriptionResponse;
import com.sambaran.codecanvas.entity.Plan;
import com.sambaran.codecanvas.entity.Subscription;
import com.sambaran.codecanvas.entity.User;
import com.sambaran.codecanvas.enums.SubscriptionStatus;
import com.sambaran.codecanvas.error.ResourceNotFoundException;
import com.sambaran.codecanvas.mapper.SubscriptionMapper;
import com.sambaran.codecanvas.repository.PlanRepository;
import com.sambaran.codecanvas.repository.SubscriptionRepository;
import com.sambaran.codecanvas.repository.UserRepository;
import com.sambaran.codecanvas.security.AuthUtil;
import com.sambaran.codecanvas.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;

/**
 * @author Sambaran
 */
@Service
@RequiredArgsConstructor
public class SubscriptionServiceimpl implements SubscriptionService {

    private final AuthUtil authUtil;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserRepository userRepository;
    private final PlanRepository planRepository;

    @Override
    public SubscriptionResponse getCurrentSubscription() {
        Long userId = authUtil.getCurrentUserId();

        var currentSubscription = subscriptionRepository
                .findByUserIdAndStatusIn(userId, Set.of(
                   SubscriptionStatus.ACTIVE, SubscriptionStatus.PAST_DUE,
                   SubscriptionStatus.TRIALING
                )).orElse(
                        new Subscription()
                        );

        return subscriptionMapper.toSubscriptionResponse(currentSubscription);
    }

    @Override
    public void activateSubscription(Long userId, Long planId, String subscriptionId, String customerId) {
        boolean exists = subscriptionRepository.existsByStripeSubscriptionId(subscriptionId);
        if(exists) return;

        User user = getUser(userId);
        Plan plan = getPlan(planId);

        Subscription subscription = Subscription.builder()
                .user(user)
                .plan(plan)
                .stripeSubscriptionId(subscriptionId)
                .status(SubscriptionStatus.INCOMPLETE)
                .build();

        subscriptionRepository.save(subscription);
    }




    @Override
    public void updateSubscription(String subscriptionId, SubscriptionStatus status, Instant periodStart, Instant periodEnd, Boolean cancelAtPeriodEnd, Long planId) {

    }

    @Override
    public void cancelSubscription(String subscriptionId) {

    }

    @Override
    public void renewSubscriptionPeriod(String subId, Instant periodStart, Instant periodEnd) {
        Subscription subscription = getSubscription(subId);

        Instant newStart = periodStart != null? periodStart:subscription.getCurrentPeriodEnd();

        subscription.setCurrentPeriodStart(newStart);
        subscription.setCurrentPeriodEnd(periodEnd);

        if(subscription.getStatus() == SubscriptionStatus.PAST_DUE || subscription.getStatus() == SubscriptionStatus.INCOMPLETE)
            subscription.setStatus(SubscriptionStatus.ACTIVE);

        subscriptionRepository.save(subscription);
    }


    @Override
    public void markSubscriptionPastDue(String subId) {

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
