package com.sambaran.codecanvas.service;

import com.sambaran.codecanvas.dto.subscription.CheckoutRequest;
import com.sambaran.codecanvas.dto.subscription.CheckoutResponse;
import com.sambaran.codecanvas.dto.subscription.PortalResponse;
import com.stripe.model.StripeObject;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Sambaran
 */
public interface PaymentProcessor {
    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request);

    PortalResponse openCustomerPortal();

    void handleWebhookEvent(String type, StripeObject stripeObject, Map<String, String> metadata);
}
