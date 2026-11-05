package com.sambaran.codecanvas.dto.member;

import com.sambaran.codecanvas.entity.Project;
import com.sambaran.codecanvas.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

/**
 * @author Sambaran
 */
public record UpdateMemberRoleRequest(@NotNull ProjectRole role) {
}
