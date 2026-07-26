package com.sambaran.codecanvas.service;

import com.sambaran.codecanvas.dto.project.ProjectRequest;
import com.sambaran.codecanvas.dto.project.ProjectResponse;
import com.sambaran.codecanvas.dto.project.ProjectSummaryResponse;

import java.util.List;

/**
 * @author Sambaran
 */
public interface ProjectService {

    List<ProjectSummaryResponse> getUserProjects(Long userId);

    ProjectResponse getUserProjectById(Long id, Long userId);

    ProjectResponse createProject(ProjectRequest request, Long userId);

    ProjectResponse updateProject(Long id, ProjectRequest request, Long userId);

    void softDelele(Long id, Long userId);
}
