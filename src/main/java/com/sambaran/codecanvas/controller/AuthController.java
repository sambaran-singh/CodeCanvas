package com.sambaran.codecanvas.controller;

import com.sambaran.codecanvas.dto.auth.AuthResponse;
import com.sambaran.codecanvas.dto.auth.LoginRequest;
import com.sambaran.codecanvas.dto.auth.SignUpRequest;
import com.sambaran.codecanvas.dto.auth.UserProfileResponse;
import com.sambaran.codecanvas.service.AuthService;
import com.sambaran.codecanvas.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sambaran
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthController {
     AuthService authService;
     UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignUpRequest request){
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile(){
        Long id = 1L;
        return ResponseEntity.ok(userService.getProfile(id));
    }
}
