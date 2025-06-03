package com.example.book_my_show.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import java.util.List;

import com.example.book_my_show.dto.request.TheatreRequestDTO;
import com.example.book_my_show.dto.response.TheatreResponseDTO;
import com.example.book_my_show.service.TheatreService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TheatreController {
    
    private final TheatreService theatreService;
    
    @GetMapping("/theatres")
    public ResponseEntity<List<TheatreResponseDTO>> getAllTheatres() {
        List<TheatreResponseDTO> theatres = theatreService.getAllTheatres();
        return ResponseEntity.ok(theatres);
    }
    
    @GetMapping("/theatres/{theatreId}")
    public ResponseEntity<TheatreResponseDTO> getTheatreById(@PathVariable Long theatreId) {
        TheatreResponseDTO theatre = theatreService.getTheatreById(theatreId);
        return ResponseEntity.ok(theatre);
    }
    
    @GetMapping("/theatres/city/{cityId}")
    public ResponseEntity<List<TheatreResponseDTO>> getTheatresByCity(@PathVariable Long cityId) {
        List<TheatreResponseDTO> theatres = theatreService.getTheatresByCity(cityId);
        return ResponseEntity.ok(theatres);
    }
    
    @PostMapping("/theatres")
    public ResponseEntity<TheatreResponseDTO> createTheatre(@RequestBody TheatreRequestDTO theatreRequestDTO) {
        TheatreResponseDTO theatre = theatreService.createTheatre(theatreRequestDTO);
        return ResponseEntity.ok(theatre);
    }
    
    @PutMapping("/theatres/{theatreId}")
    public ResponseEntity<TheatreResponseDTO> updateTheatre(@PathVariable Long theatreId, @RequestBody TheatreRequestDTO theatreRequestDTO) {
        TheatreResponseDTO theatre = theatreService.updateTheatre(theatreId, theatreRequestDTO);
        return ResponseEntity.ok(theatre);
    }
    
    @DeleteMapping("/theatres/{theatreId}")
    public ResponseEntity<Void> deleteTheatre(@PathVariable Long theatreId) {
        theatreService.deleteTheatre(theatreId);
        return ResponseEntity.ok().build();
    }
}
