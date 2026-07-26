package com.sambaran.codecanvas.dto.subscription;

/**
 * @author Sambaran
 */
public record PlanLimitsResponse(String  planName,
                                 int maxTokensPerDay,
                                 int maxProjects,
                                 boolean unlimitedAi
) {
}
