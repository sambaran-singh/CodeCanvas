package com.sambaran.codecanvas.dto.subscription;

/**
 * @author Sambaran
 */
public record UsageTodayResponse(
        Integer tokensUsed,
        Integer tokensLimit,
        Integer previewRunning,
        Integer previewsLimit
) {
}
