package com.sambaran.codecanvas.dto.project;

import com.sambaran.codecanvas.dto.auth.UserProfileResponse;

import java.time.Instant;

/**
 * @author Sambaran
 */
public record ProjectResponse(
        Long id,
          String name,
          Instant createdAt,
          Instant updated,
         UserProfileResponse owner
) {
}
