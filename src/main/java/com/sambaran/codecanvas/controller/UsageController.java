package com.sambaran.codecanvas.controller;

import com.sambaran.codecanvas.dto.subscription.PlanLimitsResponse;
import com.sambaran.codecanvas.dto.subscription.UsageTodayResponse;
import com.sambaran.codecanvas.service.UsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sambaran
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usage")
public class UsageController {
    private final UsageService usageService;

    @GetMapping("/today")
    public ResponseEntity<UsageTodayResponse> getUsageToday(){
        Long userId = 1L;
        return ResponseEntity.ok(usageService.getTodayUsageOfUser(userId) );
    }

    @GetMapping("/limits")
    public ResponseEntity<PlanLimitsResponse> getPlanLimits(){
        Long userId = 1L;
        return ResponseEntity.ok(usageService.getCurrentSubscriptionLimitsOfUser(userId));
    }

}
