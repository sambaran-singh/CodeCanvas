package com.sambaran.codecanvas.dto.auth;

/**
 * @author Sambaran
 */
public record SignUpRequest(
        String email,
        String  name,
        String password
) {
}
