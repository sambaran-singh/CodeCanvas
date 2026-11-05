package com.sambaran.codecanvas.mapper;


import com.sambaran.codecanvas.dto.subscription.PlanResponse;
import com.sambaran.codecanvas.dto.subscription.SubscriptionResponse;
import com.sambaran.codecanvas.entity.Plan;
import com.sambaran.codecanvas.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Sambaran
 */
@Mapper(componentModel = "spring")
public interface SubscriptionMapper {


    SubscriptionResponse toSubscriptionResponse(Subscription currentSubscription);

    PlanResponse toPlanResponse(Plan plan);
}
