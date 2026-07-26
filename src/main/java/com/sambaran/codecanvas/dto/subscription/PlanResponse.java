package com.sambaran.codecanvas.dto.subscription;

/**
 * @author Sambaran
 */
public record PlanResponse(
        Long id,
        String name,
        String stripePriceId,
        Integer maxProject,
        Integer maxTokensPerDay,
        Integer maxPreview,
        Boolean unlimitedAi,
        String price
) {
}
