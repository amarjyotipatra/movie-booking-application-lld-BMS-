package com.example.book_my_show.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;

import com.example.book_my_show.model.Show;

public interface ShowRepo extends JpaRepository<Show, Long> {
    
    List<Show> findByMovieId(Long movieId);
      List<Show> findByScreenId(Long screenId);
    
    @Query("SELECT s FROM shows s WHERE s.movie.id = :movieId AND s.startTime >= :startTime")
    List<Show> findByMovieIdAndStartTimeAfter(@Param("movieId") Long movieId, @Param("startTime") Date startTime);
    
    @Query("SELECT s FROM shows s WHERE s.screen.theatre.city.id = :cityId AND s.startTime >= :startTime")
    List<Show> findByCityIdAndStartTimeAfter(@Param("cityId") Long cityId, @Param("startTime") Date startTime);
}
