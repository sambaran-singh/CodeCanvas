package com.sambaran.codecanvas.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author Sambaran
 */
public record ProjectRequest(
        @NotBlank String name
) {
}
