package com.frezneel.starter.services;

import com.frezneel.starter.dto.auth.LoginRequest;
import com.frezneel.starter.dto.auth.LoginResponse;
import com.frezneel.starter.dto.user.UserRegisterRequest;
import com.frezneel.starter.models.Users;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

public interface AuthService {
    @Transactional
    LoginResponse authenticate(LoginRequest request);
    @Transactional
    Users registerUser(UserRegisterRequest request);
}
