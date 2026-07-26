package com.sambaran.codecanvas.dto.project;

import java.time.Instant;

/**
 * @author Sambaran
 */
public record ProjectSummaryResponse(
        Long id,
        String name,
        Instant createdAt,
        Instant updated
) {
}
