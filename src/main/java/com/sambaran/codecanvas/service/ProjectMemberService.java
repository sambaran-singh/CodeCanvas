package com.sambaran.codecanvas.service;

import com.sambaran.codecanvas.dto.member.InviteMemberRequest;
import com.sambaran.codecanvas.dto.member.MemberResponse;
import com.sambaran.codecanvas.dto.member.UpdateMemberRoleRequest;

import java.util.List;

/**
 * @author Sambaran
 */
public interface ProjectMemberService {
    List<MemberResponse> getProjectMember(Long projectId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request);

    void deleteProjectMember(Long projectId, Long memberId);
}
