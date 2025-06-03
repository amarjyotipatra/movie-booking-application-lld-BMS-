package com.example.book_my_show.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

import com.example.book_my_show.dto.request.TheatreRequestDTO;
import com.example.book_my_show.dto.response.TheatreResponseDTO;
import com.example.book_my_show.model.Theatre;
import com.example.book_my_show.model.City;
import com.example.book_my_show.repo.TheatreRepo;
import com.example.book_my_show.repo.CityRepo;

@Service
@RequiredArgsConstructor
public class TheatreService {
    
    private final TheatreRepo theatreRepo;
    private final CityRepo cityRepo;
    
    public List<TheatreResponseDTO> getAllTheatres() {
        return theatreRepo.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    public TheatreResponseDTO getTheatreById(Long id) {
        Theatre theatre = theatreRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Theatre not found with id: " + id));
        return convertToResponseDTO(theatre);
    }
    
    public List<TheatreResponseDTO> getTheatresByCity(Long cityId) {
        return theatreRepo.findByCityId(cityId).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    public TheatreResponseDTO createTheatre(TheatreRequestDTO request) {
        City city = cityRepo.findById(request.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found with id: " + request.getCityId()));
        
        if (theatreRepo.existsByNameAndCityId(request.getName(), request.getCityId())) {
            throw new RuntimeException("Theatre already exists with name: " + request.getName() + " in city: " + city.getName());
        }
        
        Theatre theatre = new Theatre();
        theatre.setName(request.getName());
        theatre.setCity(city);
        
        theatre = theatreRepo.save(theatre);
        return convertToResponseDTO(theatre);
    }
    
    public TheatreResponseDTO updateTheatre(Long id, TheatreRequestDTO request) {
        Theatre theatre = theatreRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Theatre not found with id: " + id));
        
        City city = cityRepo.findById(request.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found with id: " + request.getCityId()));
        
        theatre.setName(request.getName());
        theatre.setCity(city);
        theatre = theatreRepo.save(theatre);
        return convertToResponseDTO(theatre);
    }
    
    public void deleteTheatre(Long id) {
        if (!theatreRepo.existsById(id)) {
            throw new RuntimeException("Theatre not found with id: " + id);
        }
        theatreRepo.deleteById(id);
    }
    
    private TheatreResponseDTO convertToResponseDTO(Theatre theatre) {
        TheatreResponseDTO dto = new TheatreResponseDTO();
        dto.setId(theatre.getId());
        dto.setName(theatre.getName());
        dto.setCityName(theatre.getCity().getName());
        dto.setCityId(theatre.getCity().getId());
        
        if (theatre.getScreens() != null) {
            dto.setScreenNames(theatre.getScreens().stream()
                    .map(screen -> screen.getName())
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }
}
