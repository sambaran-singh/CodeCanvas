package com.sambaran.codecanvas.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.sambaran.codecanvas.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Sambaran
 */
public interface PlanRepository extends JpaRepository<Plan,Long> {

    Optional<Plan> findByStripePriceId(String id);
}
