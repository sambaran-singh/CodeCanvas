package com.sambaran.codecanvas.controller;

import com.sambaran.codecanvas.dto.member.InviteMemberRequest;
import com.sambaran.codecanvas.dto.member.MemberResponse;
import com.sambaran.codecanvas.dto.member.UpdateMemberRoleRequest;
import com.sambaran.codecanvas.dto.member.UpdateMemberRoleRequest;
import com.sambaran.codecanvas.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sambaran
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects/{id}/members")
public class ProjectMemberController {
    private final ProjectMemberService projectMemberService;

    @GetMapping
    private ResponseEntity<List<MemberResponse>> getProjectMembers(@PathVariable Long projectId){
        Long userId = 1L;

        return ResponseEntity.ok(projectMemberService.getProjectMember(projectId, userId));
    }

    @PostMapping
    private ResponseEntity<MemberResponse> inviteMember(
            @PathVariable Long projectId,
            @RequestBody InviteMemberRequest request
    ){
        Long userId = 1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(
                    projectMemberService.inviteMember(projectId, request, userId)
                );
    }

    @PatchMapping("{/memberId}")
    private ResponseEntity<MemberResponse> updateMemberRole(
            @PathVariable Long projectId,
            @PathVariable Long memberId,
            @RequestBody UpdateMemberRoleRequest request
    ){
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId, memberId, userId, request));
    }

    @DeleteMapping("{memberId}")
    private ResponseEntity<MemberResponse> deleteMember(
            @PathVariable Long projectId,
            @PathVariable Long memberId
    ){
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.deleteProjectMember(projectId, memberId, userId));
    }

}
