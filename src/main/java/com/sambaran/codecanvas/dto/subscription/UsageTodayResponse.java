package com.sambaran.codecanvas.dto.subscription;

/**
 * @author Sambaran
 */
public record UsageTodayResponse(
        int tokensUsed,
        int tokensLimit,
        int previewRunning,
        int previewsLimit
) {
}
