package com.sambaran.codecanvas.service;

import com.sambaran.codecanvas.dto.auth.AuthResponse;
import com.sambaran.codecanvas.dto.auth.LoginRequest;
import com.sambaran.codecanvas.dto.auth.SignUpRequest;

/**
 * @author Sambaran
 */
public interface AuthService {
    AuthResponse signup(SignUpRequest request);

    AuthResponse login(LoginRequest request);
}
