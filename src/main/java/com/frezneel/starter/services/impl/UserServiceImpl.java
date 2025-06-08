package com.frezneel.starter.services.impl;

import com.frezneel.starter.dto.user.UserResponse;
import com.frezneel.starter.exceptions.ResourceNotFoundException;
import com.frezneel.starter.models.Users;
import com.frezneel.starter.repositories.UserRepo;
import com.frezneel.starter.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepo.findAll().stream()
                .filter(users -> !users.getIsDeleted())
                .map(this::mapUserToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        Users users = userRepo.findById(id)
                .filter(u -> !u.getIsDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));
        return mapUserToResponseDTO(users);
    }

    @Override
    public UserResponse softDeleteUser(Long id) {
        Users users = userRepo.findById(id)
                .filter(u -> !u.getIsDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));
        users.setIsDeleted(true);
        userRepo.save(users);
        return mapUserToResponseDTO(users);
    }

    @Override
    public UserResponse mapUserToResponseDTO(Users users) {
        return new UserResponse(
                users.getId(),
                users.getUsername(),
                users.getEmail(),
                users.getRole() != null ? users.getRole().getName() : null
        );
    }
}
