package com.sambaran.codecanvas.service;

import com.sambaran.codecanvas.dto.subscription.CheckoutRequest;
import com.sambaran.codecanvas.dto.subscription.CheckoutResponse;
import com.sambaran.codecanvas.dto.subscription.PortalResponse;
import com.sambaran.codecanvas.dto.subscription.SubscriptionResponse;

/**
 * @author Sambaran
 */
public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(long userId);

    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId);

    PortalResponse openCustomerPortal(Long userId);
}
