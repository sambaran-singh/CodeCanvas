package com.sambaran.codecanvas.controller;

import com.sambaran.codecanvas.dto.member.InviteMemberRequest;
import com.sambaran.codecanvas.dto.member.MemberResponse;
import com.sambaran.codecanvas.dto.member.UpdateMemberRoleRequest;
import com.sambaran.codecanvas.dto.member.UpdateMemberRoleRequest;
import com.sambaran.codecanvas.service.ProjectMemberService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/projects/{projectId}/members")
public class ProjectMemberController {
    private final ProjectMemberService projectMemberService;

    @GetMapping
    private ResponseEntity<List<MemberResponse>> getProjectMembers(@PathVariable Long projectId){
        System.out.println(projectId);
        return ResponseEntity.ok(projectMemberService.getProjectMember(projectId));
    }

    @PostMapping
    private ResponseEntity<MemberResponse> inviteMember(
            @PathVariable Long projectId,
            @RequestBody @Valid InviteMemberRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                    projectMemberService.inviteMember(projectId, request)
                );
    }

    @PatchMapping("{memberId}")
    private ResponseEntity<MemberResponse> updateMemberRole(
            @PathVariable Long projectId,
            @PathVariable Long memberId,
            @RequestBody @Valid UpdateMemberRoleRequest request
    ){
        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId, memberId, request));
    }

    @DeleteMapping("{memberId}")
    private ResponseEntity<Void> deleteMember(
            @PathVariable Long projectId,
            @PathVariable Long memberId
    ){
        projectMemberService.deleteProjectMember(projectId, memberId);
        return ResponseEntity.noContent().build();
    }

}
