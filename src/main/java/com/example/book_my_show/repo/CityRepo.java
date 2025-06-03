package com.example.book_my_show.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.example.book_my_show.model.City;

public interface CityRepo extends JpaRepository<City, Long> {
    
    boolean existsByName(String name);
    List<City> findByNameContainingIgnoreCase(String name);
}
