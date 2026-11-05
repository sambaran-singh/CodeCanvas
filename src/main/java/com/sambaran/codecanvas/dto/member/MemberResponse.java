package com.sambaran.codecanvas.dto.member;

import com.sambaran.codecanvas.enums.ProjectRole;

import java.time.Instant;

/**
 * @author Sambaran
 */
public record MemberResponse(
        Long id,
     String username,
     String name,
    ProjectRole projectRole,
    Instant invitedAt
) {
}
