package com.sambaran.codecanvas.service.impl;

import com.sambaran.codecanvas.dto.subscription.PlanLimitsResponse;
import com.sambaran.codecanvas.dto.subscription.UsageTodayResponse;
import com.sambaran.codecanvas.service.UsageService;
import org.springframework.stereotype.Service;

/**
 * @author Sambaran
 */
@Service
public class UsageServiceimpl implements UsageService {
    @Override
    public UsageTodayResponse getTodayUsageOfUser(Long userId) {
        return null;
    }

    @Override
    public PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
