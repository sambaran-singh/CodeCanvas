package com.sambaran.codecanvas.dto.subscription;

/**
 * @author Sambaran
 */
public record PlanLimitsResponse(String  planName,
                                 Integer maxTokensPerDay,
                                 Integer maxProjects,
                                 Boolean unlimitedAi
) {
}
