package com.sambaran.codecanvas.dto.member;

import com.sambaran.codecanvas.entity.Project;
import com.sambaran.codecanvas.enums.ProjectRole;

/**
 * @author Sambaran
 */
public record UpdateMemberRoleRequest(ProjectRole role) {
}
