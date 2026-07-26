package com.sambaran.codecanvas.dto.project;

import java.time.Instant;

/**
 * @author Sambaran
 */
public record FileNode(
        String path,
        Instant modifiedAt,
        Long size,
        String  type
) {
}
