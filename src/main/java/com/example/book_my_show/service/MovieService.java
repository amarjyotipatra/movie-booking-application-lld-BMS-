package com.example.book_my_show.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

import com.example.book_my_show.dto.request.MovieRequestDTO;
import com.example.book_my_show.dto.response.MovieResponseDTO;
import com.example.book_my_show.model.Movie;
import com.example.book_my_show.repo.MovieRepo;

@Service
@RequiredArgsConstructor
public class MovieService {
    
    private final MovieRepo movieRepo;
    
    public List<MovieResponseDTO> getAllMovies() {
        return movieRepo.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    public MovieResponseDTO getMovieById(Long id) {
        Movie movie = movieRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
        return convertToResponseDTO(movie);
    }
    
    public MovieResponseDTO createMovie(MovieRequestDTO request) {
        Movie movie = new Movie();
        movie.setName(request.getName());
        
        movie = movieRepo.save(movie);
        return convertToResponseDTO(movie);
    }
    
    public MovieResponseDTO updateMovie(Long id, MovieRequestDTO request) {
        Movie movie = movieRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
        
        movie.setName(request.getName());
        movie = movieRepo.save(movie);
        return convertToResponseDTO(movie);
    }
    
    public void deleteMovie(Long id) {
        if (!movieRepo.existsById(id)) {
            throw new RuntimeException("Movie not found with id: " + id);
        }
        movieRepo.deleteById(id);
    }
    
    private MovieResponseDTO convertToResponseDTO(Movie movie) {
        MovieResponseDTO dto = new MovieResponseDTO();
        dto.setId(movie.getId());
        dto.setName(movie.getName());
        return dto;
    }
}
