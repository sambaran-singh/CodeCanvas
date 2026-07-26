package com.sambaran.codecanvas.service;

import com.sambaran.codecanvas.dto.member.InviteMemberRequest;
import com.sambaran.codecanvas.dto.member.MemberResponse;
import com.sambaran.codecanvas.dto.member.UpdateMemberRoleRequest;

import java.util.List;

/**
 * @author Sambaran
 */
public interface ProjectMemberService {
    List<MemberResponse> getProjectMember(Long projectId, Long userId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);

    MemberResponse updateMemberRole(Long projectId, Long memberId, Long userId, UpdateMemberRoleRequest request);

    MemberResponse deleteProjectMember(Long projectId, Long memberId, Long userId);
}
