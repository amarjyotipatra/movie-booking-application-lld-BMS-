package com.example.book_my_show.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.book_my_show.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    Optional<User> findByUserName(String userName);
    
    boolean existsByEmail(String email);
}
