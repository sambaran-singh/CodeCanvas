package com.sambaran.codecanvas.entity;

import com.sambaran.codecanvas.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

/**
 * @author Sambaran
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(nullable = false, name = "plan_id")
    Plan plan;

    String stripeSubscriptionId;

    @Enumerated(value = EnumType.STRING)
    SubscriptionStatus status;

    Instant currentPeriodStart;
    Instant currentPeriodEnd;
    Boolean cancelAtPeriodEnd;
    @CreationTimestamp
    Instant createdAt;

    @UpdateTimestamp
    Instant updatedAt;

}
