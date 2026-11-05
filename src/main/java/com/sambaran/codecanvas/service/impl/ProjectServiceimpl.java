package com.sambaran.codecanvas.service.impl;

import com.sambaran.codecanvas.dto.project.ProjectRequest;
import com.sambaran.codecanvas.dto.project.ProjectResponse;
import com.sambaran.codecanvas.dto.project.ProjectSummaryResponse;
import com.sambaran.codecanvas.entity.Project;
import com.sambaran.codecanvas.entity.ProjectMember;
import com.sambaran.codecanvas.entity.ProjectMemberId;
import com.sambaran.codecanvas.entity.User;
import com.sambaran.codecanvas.enums.ProjectRole;
import com.sambaran.codecanvas.error.BadRequestException;
import com.sambaran.codecanvas.error.ResourceNotFoundException;
import com.sambaran.codecanvas.mapper.ProjectMapper;
import com.sambaran.codecanvas.repository.ProjectMemberRepository;
import com.sambaran.codecanvas.repository.ProjectRepository;
import com.sambaran.codecanvas.repository.UserRepository;
import com.sambaran.codecanvas.security.AuthUtil;
import com.sambaran.codecanvas.service.ProjectService;
import com.sambaran.codecanvas.service.SubscriptionService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sambaran
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceimpl implements ProjectService {
    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;
    ProjectMemberRepository projectMemberRepository;
    AuthUtil authUtil;
    SubscriptionService subscriptionService;

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        if(!subscriptionService.canCreateNewProject()){
            throw new BadRequestException("User can't create new project with current plan.Upgrage Plan now !!!");
        }
        Long userId = authUtil.getCurrentUserId();
//        User owner = userRepository.findById(userId).orElseThrow(
//                ()->new ResourceNotFoundException("User",userId.toString())
//        );

        User owner = userRepository.getReferenceById(userId);
        Project project = Project.builder()
                .name(request.name())
                .isPublic(false)
                .build();

        project = projectRepository.save(project);
        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.getId());
        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .projectRole(ProjectRole.OWNER)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .user(owner)
                .project(project)
                .build();

        projectMemberRepository.save(projectMember);
        return projectMapper.toProjectResponse(project);
    }
    @Override
    public List<ProjectSummaryResponse> getUserProjects() {
//        return projectRepository.findAllAccessibleProjects(userId)
//                .stream()
//                .map(projectMapper::toProjectSummaryResponse)
//                .collect(Collectors.toList());

        //or
        Long userId = authUtil.getCurrentUserId();
        var projects = projectRepository.findAllAccessibleProjects(userId);
        return projectMapper.toProjectSummaryResponseList(projects);
    }

    @Override
    @PreAuthorize("@security.canViewProject(#projectId)")
    public ProjectResponse getUserProjectById(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

        return projectMapper.toProjectResponse(project);
    }



    @Override
    @PreAuthorize("@security.canEditProject(#projectId)")
    public ProjectResponse updateProject(Long projectId, ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

        project.setName(request.name());
        projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    @PreAuthorize("@security.canDeleteProject(#projectId)")
    public void softDelele(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    // internal function
    Project getAccessibleProjectById(Long id, Long userId){
        Project project = projectRepository.findAccessibleProjectById(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Project",id.toString()) );
        return project;
    }

}
