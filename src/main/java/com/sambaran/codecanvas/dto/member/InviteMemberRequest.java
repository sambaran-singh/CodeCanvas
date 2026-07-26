package com.sambaran.codecanvas.dto.member;

import com.sambaran.codecanvas.enums.ProjectRole;

/**
 * @author Sambaran
 */
public record InviteMemberRequest(
        String email,
        ProjectRole role
) {
}
