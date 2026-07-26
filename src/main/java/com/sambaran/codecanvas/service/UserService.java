package com.sambaran.codecanvas.service;

import com.sambaran.codecanvas.dto.auth.UserProfileResponse;

/**
 * @author Sambaran
 */
public interface UserService {
    UserProfileResponse getProfile(Long id);
}
