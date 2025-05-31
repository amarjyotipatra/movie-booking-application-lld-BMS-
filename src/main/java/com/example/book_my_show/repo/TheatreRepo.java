package com.example.book_my_show.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_my_show.model.Theatre;

public interface TheatreRepo extends JpaRepository<Theatre, Long> {
    
}
