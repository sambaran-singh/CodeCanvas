package com.sambaran.codecanvas.entity;

import com.sambaran.codecanvas.enums.SubscriptionStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

/**
 * @author Sambaran
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Subscription {
    Long id;
    User user;
    Plan plan;
    String stripeCustomerId;
    String stripeSubscriptionId;
    Instant currentPeriodStart;
    Instant currentPeriodEnd;
    Boolean cancelAtPeriodEnd;
    Instant createdAt;
    Instant updatedAt;
    SubscriptionStatus status;
}
