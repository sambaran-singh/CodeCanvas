package com.sambaran.codecanvas.mapper;

import com.sambaran.codecanvas.dto.auth.SignUpRequest;
import com.sambaran.codecanvas.dto.auth.UserProfileResponse;
import com.sambaran.codecanvas.entity.User;
import org.mapstruct.Mapper;

/**
 * @author Sambaran
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignUpRequest request);
    UserProfileResponse toUserProfileResponse(User user);
}
