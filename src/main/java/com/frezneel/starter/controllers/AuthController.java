package com.frezneel.starter.controllers;

import com.frezneel.starter.dto.auth.LoginRequest;
import com.frezneel.starter.dto.auth.LoginResponse;
import com.frezneel.starter.dto.user.UserRegisterRequest;
import com.frezneel.starter.dto.user.UserResponse;
import com.frezneel.starter.models.Users;
import com.frezneel.starter.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/check")
    public ResponseEntity<?> authCheck(){
        Map<String, String> dataTest = new HashMap<>();
        dataTest.put("Info", "API Berhasil komunikasi");
        return ResponseEntity.ok(dataTest);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authUser(@Valid @RequestBody LoginRequest loginRequest){
        LoginResponse response = authService.authenticate(loginRequest);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest){
        Users newUser = authService.registerUser(userRegisterRequest);
        UserResponse userResponse = new UserResponse(
                newUser.getId(),
                newUser.getUsername(),
                newUser.getEmail(),
                newUser.getRole().getName()
        );
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

}
