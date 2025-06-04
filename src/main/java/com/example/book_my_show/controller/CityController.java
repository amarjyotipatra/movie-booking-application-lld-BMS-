package com.example.book_my_show.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import java.util.List;
import jakarta.validation.Valid;

import com.example.book_my_show.dto.request.CityRequestDTO;
import com.example.book_my_show.dto.response.CityResponseDTO;
import com.example.book_my_show.service.CityService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CityController {
    
    private final CityService cityService;
    
    @GetMapping("/cities")
    public ResponseEntity<List<CityResponseDTO>> getAllCities() {
        List<CityResponseDTO> cities = cityService.getAllCities();
        return ResponseEntity.ok(cities);
    }
    
    @GetMapping("/cities/{cityId}")
    public ResponseEntity<CityResponseDTO> getCityById(@PathVariable Long cityId) {
        CityResponseDTO city = cityService.getCityById(cityId);
        return ResponseEntity.ok(city);
    }
      @GetMapping("/cities/search")
    public ResponseEntity<List<CityResponseDTO>> searchCities(@RequestParam String name) {
        List<CityResponseDTO> cities = cityService.searchCitiesByName(name);
        return ResponseEntity.ok(cities);
    }
      @PostMapping("/cities")
    public ResponseEntity<CityResponseDTO> createCity(@Valid @RequestBody CityRequestDTO cityRequestDTO) {
        CityResponseDTO city = cityService.createCity(cityRequestDTO);
        return ResponseEntity.ok(city);
    }
    
    @PutMapping("/cities/{cityId}")
    public ResponseEntity<CityResponseDTO> updateCity(@PathVariable Long cityId, @RequestBody CityRequestDTO cityRequestDTO) {
        CityResponseDTO city = cityService.updateCity(cityId, cityRequestDTO);
        return ResponseEntity.ok(city);
    }
    
    @DeleteMapping("/cities/{cityId}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long cityId) {
        cityService.deleteCity(cityId);
        return ResponseEntity.ok().build();
    }
}
