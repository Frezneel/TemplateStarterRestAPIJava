package com.frezneel.starter.services;

import com.frezneel.starter.dto.user.UserResponse;
import com.frezneel.starter.models.Users;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    UserResponse softDeleteUser(Long id);
    UserResponse mapUserToResponseDTO(Users users);
}
