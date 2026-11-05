package com.sambaran.codecanvas.mapper;

import com.sambaran.codecanvas.dto.project.ProjectResponse;
import com.sambaran.codecanvas.dto.project.ProjectSummaryResponse;
import com.sambaran.codecanvas.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author Sambaran
 */
@Mapper(componentModel = "spring")
public interface ProjectMapper {
    @Mapping(source ="updatedAt", target = "updated")
    ProjectResponse toProjectResponse(Project project);
    ProjectSummaryResponse toProjectSummaryResponse(Project project);
    List<ProjectSummaryResponse> toProjectSummaryResponseList(List<Project> projectList);
}
