package com.sambaran.codecanvas.dto.auth;

import com.sambaran.codecanvas.entity.User;

/**
 * @author Sambaran
 */
public record AuthResponse(
        String token,
        UserProfileResponse user
) {
}
