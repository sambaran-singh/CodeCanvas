package com.sambaran.codecanvas.dto.project;

import java.time.Instant;

/**
 * @author Sambaran
 */
public record FileNode(
        String path
) {


    @Override
    public String toString() {
        return path;
    }
}
