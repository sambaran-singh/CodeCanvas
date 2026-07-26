package com.sambaran.codecanvas.service;

import com.sambaran.codecanvas.dto.subscription.PlanLimitsResponse;
import com.sambaran.codecanvas.dto.subscription.UsageTodayResponse;

/**
 * @author Sambaran
 */
public interface UsageService {
    UsageTodayResponse getTodayUsageOfUser(Long userId);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
