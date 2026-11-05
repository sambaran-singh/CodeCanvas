package com.sambaran.codecanvas.service;

import com.sambaran.codecanvas.dto.subscription.CheckoutRequest;
import com.sambaran.codecanvas.dto.subscription.CheckoutResponse;
import com.sambaran.codecanvas.dto.subscription.PortalResponse;
import com.sambaran.codecanvas.dto.subscription.SubscriptionResponse;
import com.sambaran.codecanvas.enums.SubscriptionStatus;

import java.time.Instant;

/**
 * @author Sambaran
 */
public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription();


    void activateSubscription(Long userId, Long planId, String subscriptionId, String customerId);

    void updateSubscription(String subscriptionId, SubscriptionStatus status, Instant periodStart, Instant periodEnd, Boolean cancelAtPeriodEnd, Long planId);

    void cancelSubscription(String subscriptionId);

    void renewSubscriptionPeriod(String subId, Instant periodStart, Instant periodEnd);

    void markSubscriptionPastDue(String subId);
}
