package com.example.book_my_show.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.example.book_my_show.model.Booking;
import com.example.book_my_show.model.BookingStatus;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    
    List<Booking> findByUserCreatedById(Long userId);
    
    List<Booking> findByBookingStatus(BookingStatus status);
    
    @Query("SELECT b FROM Booking b WHERE b.show.id = :showId")
    List<Booking> findByShowId(@Param("showId") Long showId);
}
