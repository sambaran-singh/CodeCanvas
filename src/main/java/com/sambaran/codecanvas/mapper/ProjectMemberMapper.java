package com.sambaran.codecanvas.mapper;

import com.sambaran.codecanvas.dto.member.MemberResponse;
import com.sambaran.codecanvas.entity.ProjectMember;
import com.sambaran.codecanvas.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Sambaran
 */
@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {
    @Mapping(target = "projectRole", constant = "OWNER")
    MemberResponse toProjectMemberResponseFromOwner(User owner);

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "name", source = "user.name")
    MemberResponse toProjectMemberResponseFromMember(ProjectMember projectMember);
}
