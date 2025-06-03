package com.example.book_my_show.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.example.book_my_show.model.Seat;
import com.example.book_my_show.model.SeatType;

public interface SeatRepo extends JpaRepository<Seat, Long> {
    
    List<Seat> findByType(SeatType type);
}
