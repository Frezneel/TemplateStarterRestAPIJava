package com.frezneel.starter.services.impl;

import com.frezneel.starter.services.JwtService;
import com.frezneel.starter.dto.auth.LoginRequest;
import com.frezneel.starter.dto.auth.LoginResponse;
import com.frezneel.starter.dto.user.UserRegisterRequest;
import com.frezneel.starter.exceptions.AuthException;
import com.frezneel.starter.exceptions.ResourceNotFoundException;
import com.frezneel.starter.models.Roles;
import com.frezneel.starter.models.Users;
import com.frezneel.starter.repositories.RoleRepo;
import com.frezneel.starter.repositories.UserRepo;
import com.frezneel.starter.services.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    @Transactional
    public LoginResponse authenticate(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsernameOrEmail(),
                            request.getPassword()
                    )
            );

            Users users = (Users) authentication.getPrincipal();
            String jwtToken = jwtService.generateToken(users);
            return new LoginResponse(jwtToken, users.getUsername(), users.getRole().getName());
        }catch (AuthenticationException e){
            throw new AuthException("Invalid username or password");
        }
    }

    @Override
    @Transactional
    public Users registerUser(UserRegisterRequest request) {
        if (userRepo.existsByUsername(request.getUsername())){
            throw new IllegalArgumentException("Username is already taken");
        }
        if (userRepo.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Email is already registered");
        }

        Roles defaultRole = roleRepo.findById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + request.getRoleId()));

        Users newUsers = new Users();
        newUsers.setUsername(request.getUsername());
        newUsers.setPassword(passwordEncoder.encode(request.getPassword()));
        newUsers.setEmail(request.getEmail());
        newUsers.setRole(defaultRole);

        return userRepo.save(newUsers);
    }
}
