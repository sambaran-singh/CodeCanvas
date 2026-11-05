package com.sambaran.codecanvas.service;

import com.sambaran.codecanvas.dto.project.ProjectRequest;
import com.sambaran.codecanvas.dto.project.ProjectResponse;
import com.sambaran.codecanvas.dto.project.ProjectSummaryResponse;

import java.util.List;

/**
 * @author Sambaran
 */
public interface ProjectService {

    List<ProjectSummaryResponse> getUserProjects();

    ProjectResponse getUserProjectById(Long id);

    ProjectResponse createProject(ProjectRequest request);

    ProjectResponse updateProject(Long id, ProjectRequest request);

    void softDelele(Long id);
}
