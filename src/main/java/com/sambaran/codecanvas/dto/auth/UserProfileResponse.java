package com.sambaran.codecanvas.dto.auth;

/**
 * @author Sambaran
 */
public record UserProfileResponse(
        Long id,
        String email,
        String name,
        String avatarUrl
) {
}
