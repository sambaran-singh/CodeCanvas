package com.sambaran.codecanvas.dto.member;

import com.sambaran.codecanvas.enums.ProjectRole;

import java.time.Instant;

/**
 * @author Sambaran
 */
public record MemberResponse(
        Long id,
     String email,
     String name,
     String avatarUrl,
    ProjectRole role,
    Instant invitedAt
) {
}
