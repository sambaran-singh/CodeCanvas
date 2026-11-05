package com.sambaran.codecanvas.service.impl;

import com.sambaran.codecanvas.dto.member.InviteMemberRequest;
import com.sambaran.codecanvas.dto.member.MemberResponse;
import com.sambaran.codecanvas.dto.member.UpdateMemberRoleRequest;
import com.sambaran.codecanvas.entity.Project;
import com.sambaran.codecanvas.entity.ProjectMember;
import com.sambaran.codecanvas.entity.ProjectMemberId;
import com.sambaran.codecanvas.entity.User;
import com.sambaran.codecanvas.mapper.ProjectMemberMapper;
import com.sambaran.codecanvas.repository.ProjectMemberRepository;
import com.sambaran.codecanvas.repository.ProjectRepository;
import com.sambaran.codecanvas.repository.UserRepository;
import com.sambaran.codecanvas.security.AuthUtil;
import com.sambaran.codecanvas.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sambaran
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectMemberServiceimpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    UserRepository userRepository;
    AuthUtil authUtil;

    @Override
    @PreAuthorize("@security.canViewMembers(#projectId)")
    public List<MemberResponse> getProjectMember(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

        System.out.println(projectId +" "+ project);
        List<MemberResponse> memberResponseList = projectMemberRepository.findByIdProjectId(project.getId())
                .stream()
                .map(projectMemberMapper::toProjectMemberResponseFromMember)
                .toList();

        return memberResponseList;
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);
        User invitee = userRepository.findByUsername(request.username()).orElseThrow();

        if(invitee.getId().equals(userId)){
            throw new RuntimeException("cannot invite yourself ");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());

        if(projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("already invited");
        }

        ProjectMember member = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .invitedAt(Instant.now())
                .projectRole(request.role())
                .build();

        projectMemberRepository.save(member);
        return projectMemberMapper.toProjectMemberResponseFromMember(member);

    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();

        projectMember.setProjectRole(request.role());
        projectMemberRepository.save(projectMember);




        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public void deleteProjectMember(Long projectId, Long memberId) {

        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        if(!projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("MEMBER NOT FOUND IN PROJECT");
        }

        projectMemberRepository.deleteById(projectMemberId);
    }


    // internal function
    Project getAccessibleProjectById(Long id, Long userId){
        Project project = projectRepository.findAccessibleProjectById(id, userId).orElseThrow();
        return project;
    }
}
