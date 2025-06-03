package com.example.book_my_show.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import java.util.List;

import com.example.book_my_show.dto.request.ShowRequestDTO;
import com.example.book_my_show.dto.response.ShowResponseDTO;
import com.example.book_my_show.service.ShowService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ShowController {
    
    private final ShowService showService;
    
    @GetMapping("/shows")
    public ResponseEntity<List<ShowResponseDTO>> getAllShows() {
        List<ShowResponseDTO> shows = showService.getAllShows();
        return ResponseEntity.ok(shows);
    }
    
    @GetMapping("/shows/{showId}")
    public ResponseEntity<ShowResponseDTO> getShowById(@PathVariable Long showId) {
        ShowResponseDTO show = showService.getShowById(showId);
        return ResponseEntity.ok(show);
    }
    
    @GetMapping("/shows/movie/{movieId}")
    public ResponseEntity<List<ShowResponseDTO>> getShowsByMovie(@PathVariable Long movieId) {
        List<ShowResponseDTO> shows = showService.getShowsByMovie(movieId);
        return ResponseEntity.ok(shows);
    }
    
    @GetMapping("/shows/city/{cityId}")
    public ResponseEntity<List<ShowResponseDTO>> getShowsByCity(@PathVariable Long cityId) {
        List<ShowResponseDTO> shows = showService.getShowsByCity(cityId);
        return ResponseEntity.ok(shows);
    }
    
    @PostMapping("/shows")
    public ResponseEntity<ShowResponseDTO> createShow(@RequestBody ShowRequestDTO showRequestDTO) {
        ShowResponseDTO show = showService.createShow(showRequestDTO);
        return ResponseEntity.ok(show);
    }
}
