package com.sambaran.codecanvas.dto.subscription;

import java.time.Instant;

/**
 * @author Sambaran
 */
public record SubscriptionResponse(
        PlanResponse plan,
        String status,
        Instant currentPeriodEnd,
        Long tokenUsedThisCycle
) {
}
