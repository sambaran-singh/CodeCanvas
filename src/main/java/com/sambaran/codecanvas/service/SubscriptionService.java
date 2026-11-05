package com.sambaran.codecanvas.service;

import com.sambaran.codecanvas.dto.subscription.SubscriptionResponse;
import com.sambaran.codecanvas.enums.SubscriptionStatus;

import java.time.Instant;

/**
 * @author Sambaran
 */
public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription();


    void activateSubscription(Long userId, Long planId, String gatewaySubscriptionId, String customerId);

    void updateSubscription(String gatewaySubscriptionId, SubscriptionStatus status, Instant periodStart, Instant periodEnd, Boolean cancelAtPeriodEnd, Long planId);

    void cancelSubscription(String gatewaySubscriptionId);

    void renewSubscriptionPeriod(String gatewaySubscriptionId, Instant periodStart, Instant periodEnd);

    void markSubscriptionPastDue(String gatewaySubscriptionId);

    boolean canCreateNewProject();
}
