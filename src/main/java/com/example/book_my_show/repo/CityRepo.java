package com.example.book_my_show.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_my_show.model.City;

public interface CityRepo extends JpaRepository<City, Integer>{
    
}
