package com.sambaran.codecanvas.repository;

import com.sambaran.codecanvas.entity.Subscription;
import com.sambaran.codecanvas.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

/**
 * @author Sambaran
 */
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {


    Optional<Subscription> findByUserIdAndStatusIn(Long userId, Set<SubscriptionStatus> statusSet);

    boolean existsByStripeSubscriptionId(String subscriptionId);

    Optional<Subscription> findByStripeSubscriptionId(String subId);
}
