package com.sambaran.codecanvas.repository;

import com.sambaran.codecanvas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Sambaran
 */
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
