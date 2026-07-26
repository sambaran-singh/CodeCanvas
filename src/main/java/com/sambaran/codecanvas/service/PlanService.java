package com.sambaran.codecanvas.service;

import com.sambaran.codecanvas.dto.subscription.PlanResponse;

import java.util.List;

/**
 * @author Sambaran
 */
public interface PlanService {


    List<PlanResponse> getAllActivePlans();
}
