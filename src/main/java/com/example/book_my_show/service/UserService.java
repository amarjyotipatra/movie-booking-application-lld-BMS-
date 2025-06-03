package com.example.book_my_show.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

import com.example.book_my_show.dto.request.UserRequestDTO;
import com.example.book_my_show.dto.response.UserResponseDTO;
import com.example.book_my_show.model.User;
import com.example.book_my_show.repo.UserRepo;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepo userRepo;
    
    public List<UserResponseDTO> getAllUsers() {
        return userRepo.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    public UserResponseDTO getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return convertToResponseDTO(user);
    }
    
    public UserResponseDTO createUser(UserRequestDTO request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User already exists with email: " + request.getEmail());
        }
        
        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // In real app, hash this password
        
        user = userRepo.save(user);
        return convertToResponseDTO(user);
    }
    
    public UserResponseDTO updateUser(Long id, UserRequestDTO request) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        // Don't update password in a simple update - should be separate endpoint
        
        user = userRepo.save(user);
        return convertToResponseDTO(user);
    }
    
    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepo.deleteById(id);
    }
    
    private UserResponseDTO convertToResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        // Don't expose password
        return dto;
    }
}
