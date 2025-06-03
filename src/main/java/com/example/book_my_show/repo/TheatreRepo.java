package com.example.book_my_show.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.example.book_my_show.model.Theatre;

public interface TheatreRepo extends JpaRepository<Theatre, Long> {
    
    List<Theatre> findByCityId(Long cityId);
    boolean existsByNameAndCityId(String name, Long cityId);
    List<Theatre> findByNameContainingIgnoreCase(String name);
}
