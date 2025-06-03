package com.example.book_my_show.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import java.util.List;

import com.example.book_my_show.dto.request.MovieRequestDTO;
import com.example.book_my_show.dto.response.MovieResponseDTO;
import com.example.book_my_show.service.MovieService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MovieController {
    
    private final MovieService movieService;
    
    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponseDTO>> getAllMovies() {
        List<MovieResponseDTO> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }
    
    @GetMapping("/movies/{movieId}")
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable Long movieId) {
        MovieResponseDTO movie = movieService.getMovieById(movieId);
        return ResponseEntity.ok(movie);
    }
    
    @PostMapping("/movies")
    public ResponseEntity<MovieResponseDTO> createMovie(@RequestBody MovieRequestDTO movieRequestDTO) {
        MovieResponseDTO movie = movieService.createMovie(movieRequestDTO);
        return ResponseEntity.ok(movie);
    }
    
    @PutMapping("/movies/{movieId}")
    public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable Long movieId, @RequestBody MovieRequestDTO movieRequestDTO) {
        MovieResponseDTO movie = movieService.updateMovie(movieId, movieRequestDTO);
        return ResponseEntity.ok(movie);
    }
    
    @DeleteMapping("/movies/{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.ok().build();
    }
}
