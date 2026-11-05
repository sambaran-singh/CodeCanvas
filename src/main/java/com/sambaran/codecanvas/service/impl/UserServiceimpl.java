package com.sambaran.codecanvas.service.impl;

import com.sambaran.codecanvas.dto.auth.UserProfileResponse;
import com.sambaran.codecanvas.error.ResourceNotFoundException;
import com.sambaran.codecanvas.repository.UserRepository;
import com.sambaran.codecanvas.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Sambaran
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class UserServiceimpl implements UserService, UserDetailsService {

    UserRepository userRepository;
    @Override
    public UserProfileResponse getProfile(Long id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User",username));
    }
}
