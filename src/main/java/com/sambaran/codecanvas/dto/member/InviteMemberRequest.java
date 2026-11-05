package com.sambaran.codecanvas.dto.member;

import com.sambaran.codecanvas.enums.ProjectRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

/**
 * @author Sambaran
 */
public record InviteMemberRequest(
        @Email String username,
        @NotNull ProjectRole role
) {
}
