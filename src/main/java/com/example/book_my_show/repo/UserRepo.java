package com.example.book_my_show.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_my_show.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    
}
