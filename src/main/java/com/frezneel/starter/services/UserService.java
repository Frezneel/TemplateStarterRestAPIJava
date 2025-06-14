package com.frezneel.starter.services;

import com.frezneel.starter.dto.user.UserResponse;
import com.frezneel.starter.models.Users;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    List<UserResponse> getAllUsers();
    @Transactional
    UserResponse getUserById(Long id);
    @Transactional
    UserResponse softDeleteUser(Long id);
    @Transactional
    UserResponse mapUserToResponseDTO(Users users);
}
