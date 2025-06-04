package com.example.book_my_show.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

import com.example.book_my_show.model.ShowSeat;
import com.example.book_my_show.model.ShowSeatStatus;

public interface ShowSeatRepo extends JpaRepository<ShowSeat, Long> {
      @Query("SELECT ss FROM ShowSeat ss WHERE ss.show.id = :showId AND ss.seat.id = :seatId")
    Optional<ShowSeat> findByShowIdAndSeatId(@Param("showId") Long showId, @Param("seatId") Long seatId);
    
    List<ShowSeat> findByShowId(Long showId);
    
    List<ShowSeat> findByShowIdAndShowSeatStatus(Long showId, ShowSeatStatus status);
}
