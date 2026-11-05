package com.sambaran.codecanvas.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/**
 * @author Sambaran
 */
@RequiredArgsConstructor
@Getter
public enum ProjectRole {
    EDITOR(Set.of(ProjectPermission.EDIT,ProjectPermission.VIEW,ProjectPermission.VIEW_MEMBERS)),
    VIEWER(Set.of(ProjectPermission.VIEW,ProjectPermission.VIEW_MEMBERS)),
    OWNER(Set.of(ProjectPermission.EDIT, ProjectPermission.VIEW,ProjectPermission.DELETE,ProjectPermission.MANAGE_MEMBERS,ProjectPermission.VIEW_MEMBERS));

    ProjectRole(ProjectPermission... permissions) {
        this.permissions = Set.of(permissions);
    }

    private final Set<ProjectPermission> permissions;
}
