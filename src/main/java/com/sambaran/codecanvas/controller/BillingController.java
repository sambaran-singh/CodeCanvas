package com.sambaran.codecanvas.controller;

import com.sambaran.codecanvas.dto.subscription.*;
import com.sambaran.codecanvas.service.PaymentProcessor;
import com.sambaran.codecanvas.service.PlanService;
import com.sambaran.codecanvas.service.SubscriptionService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sambaran
 */
@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class BillingController {
    PlanService planService;
    SubscriptionService subscriptionService;
    PaymentProcessor paymentProcessor;

    @Value("${stripe.webhook.secret}")
    @NonFinal
    String webhookSecret;
    @GetMapping("/api/plans")
    public ResponseEntity<List<PlanResponse>> getAllPlans(){
        return ResponseEntity.ok(planService.getAllActivePlans());
    }

    @GetMapping("/api/me/subscriptions")
    public ResponseEntity<SubscriptionResponse> getMySubscription(){
        return ResponseEntity.ok(subscriptionService.getCurrentSubscription());
    }

    @PostMapping("/api/stripe/checkout")
    public ResponseEntity<CheckoutResponse> createCheckoutResponse(
            @RequestBody CheckoutRequest request
            ){
        return ResponseEntity.ok(paymentProcessor.createCheckoutSessionUrl(request));
    }

    @PostMapping("/api/stripe/portal")
    public ResponseEntity<PortalResponse> openCustomerPortal() {
        return ResponseEntity.ok(paymentProcessor.openCustomerPortal());
    }

    @PostMapping("/webhooks/payment")
    public ResponseEntity<String> handlePaymentWebhooks(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader) {

        final Event event;

        // 1. Verify that the webhook genuinely came from Stripe
        try {
            event = Webhook.constructEvent(
                    payload,
                    sigHeader,
                    webhookSecret
            );
        } catch (SignatureVerificationException e) {
            log.warn("Invalid Stripe webhook signature");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid signature");
        } catch (IllegalArgumentException e) {
            log.warn("Invalid Stripe webhook payload");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid payload");
        }

        // 2. Deserialize the event object
        EventDataObjectDeserializer deserializer =
                event.getDataObjectDeserializer();

        StripeObject stripeObject;

        if (deserializer.getObject().isPresent()) {
            stripeObject = deserializer.getObject().get();
        } else {
            try {
                stripeObject = deserializer.deserializeUnsafe();
            } catch (Exception e) {
                log.error("Failed to deserialize Stripe event: {}",
                        event.getType(), e);

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Deserialization failed");
            }
        }

        if (stripeObject == null) {
            log.error("Stripe object is null for event: {}", event.getType());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Event object unavailable");
        }

        // 3. Extract metadata when the event contains a Checkout Session
        Map<String, String> metadata = new HashMap<>();

        if (stripeObject instanceof Session session
                && session.getMetadata() != null) {
            metadata = session.getMetadata();
        }

        // 4. Process the event
        try {
            paymentProcessor.handleWebhookEvent(
                    event.getType(),
                    stripeObject,
                    metadata
            );

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            log.error("Failed to process Stripe event: {}",
                    event.getType(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Webhook processing failed");
        }
    }

}
