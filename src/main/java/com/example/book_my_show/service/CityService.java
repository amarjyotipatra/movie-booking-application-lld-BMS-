package com.example.book_my_show.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

import com.example.book_my_show.dto.request.CityRequestDTO;
import com.example.book_my_show.dto.response.CityResponseDTO;
import com.example.book_my_show.model.City;
import com.example.book_my_show.repo.CityRepo;
import com.example.book_my_show.repo.TheatreRepo;

@Service
@RequiredArgsConstructor
public class CityService {
    
    private final CityRepo cityRepo;
    private final TheatreRepo theatreRepo;
    
    public List<CityResponseDTO> getAllCities() {
        return cityRepo.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    public CityResponseDTO getCityById(Long id) {
        City city = cityRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found with id: " + id));
        return convertToResponseDTO(city);
    }
    
    public List<CityResponseDTO> searchCitiesByName(String name) {
        return cityRepo.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    public CityResponseDTO createCity(CityRequestDTO request) {
        if (cityRepo.existsByName(request.getName())) {
            throw new RuntimeException("City already exists with name: " + request.getName());
        }
        
        City city = new City();
        city.setName(request.getName());
        
        city = cityRepo.save(city);
        return convertToResponseDTO(city);
    }
    
    public CityResponseDTO updateCity(Long id, CityRequestDTO request) {
        City city = cityRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found with id: " + id));
        
        city.setName(request.getName());
        city = cityRepo.save(city);
        return convertToResponseDTO(city);
    }
    
    public void deleteCity(Long id) {
        if (!cityRepo.existsById(id)) {
            throw new RuntimeException("City not found with id: " + id);
        }
        cityRepo.deleteById(id);
    }
    
    private CityResponseDTO convertToResponseDTO(City city) {
        CityResponseDTO dto = new CityResponseDTO();
        dto.setId(city.getId());
        dto.setName(city.getName());
        
        // Get theatre names in this city
        dto.setTheatreNames(theatreRepo.findByCityId(city.getId()).stream()
                .map(theatre -> theatre.getName())
                .collect(Collectors.toList()));
        
        return dto;
    }
}
