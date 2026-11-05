package com.sambaran.codecanvas.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author Sambaran
 */
public record JwtUserPrincipal(
        Long userId,
        String username,
        List<GrantedAuthority> authorities
) {
}
