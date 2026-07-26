package com.sambaran.codecanvas.dto.auth;

/**
 * @author Sambaran
 */
public record LoginRequest(
        String email,
        String password
) {
}
