package com.sambaran.codecanvas.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

/**
 * @author Sambaran
 */
public record LoginRequest(
        @Email String username,
        @Size(min = 4 , max = 50) String password
) {
}
