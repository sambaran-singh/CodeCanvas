package com.sambaran.codecanvas.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

/**
 * @author Sambaran
 */
public record SignUpRequest(
        @Email String username,
        @Size(min = 4, max = 30) String  name,
        @Size(min = 4,max = 50) String password
) {
}
