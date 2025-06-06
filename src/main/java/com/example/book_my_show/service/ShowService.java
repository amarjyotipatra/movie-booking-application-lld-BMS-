package com.example.book_my_show.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.book_my_show.dto.request.ShowRequestDTO;
import com.example.book_my_show.dto.response.ShowResponseDTO;
import com.example.book_my_show.dto.response.SeatResponseDTO;
import com.example.book_my_show.model.*;
import com.example.book_my_show.repo.*;

@Service
@RequiredArgsConstructor
public class ShowService {
    
    private final ShowRepo showRepo;
    private final MovieRepo movieRepo;
    private final ScreenRepo screenRepo;
    private final ShowSeatRepo showSeatRepo;
    
    public List<ShowResponseDTO> getAllShows() {
        return showRepo.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    public ShowResponseDTO getShowById(Long id) {
        Show show = showRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found with id: " + id));
        return convertToResponseDTO(show);
    }
    
    public List<ShowResponseDTO> getShowsByMovie(Long movieId) {
        return showRepo.findByMovieIdAndStartTimeAfter(movieId, new Date())
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    public List<ShowResponseDTO> getShowsByCity(Long cityId) {
        return showRepo.findByCityIdAndStartTimeAfter(cityId, new Date())
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    public ShowResponseDTO createShow(ShowRequestDTO request) {
        Movie movie = movieRepo.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        Screen screen = screenRepo.findById(request.getScreenId())
                .orElseThrow(() -> new RuntimeException("Screen not found"));
          Show show = new Show();
        show.setMovie(movie);
        show.setScreen(screen);
        show.setStartTime(request.getStartTime());
        show.setEndTime(request.getEndTime());
        show.setFeatures(request.getFeatures());
        
        show = showRepo.save(show);
        
        // Create ShowSeats for all seats in the screen
        createShowSeats(show, screen);
        
        return convertToResponseDTO(show);
    }
    
    private void createShowSeats(Show show, Screen screen) {
        screen.getSeats().forEach(seat -> {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setShow(show);
            showSeat.setSeat(seat);
            showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
            showSeatRepo.save(showSeat);
        });
    }
    
    private ShowResponseDTO convertToResponseDTO(Show show) {
        ShowResponseDTO dto = new ShowResponseDTO();
        dto.setId(show.getId());
        dto.setMovieName(show.getMovie().getName());
        dto.setTheatreName("Theatre Name"); // Simplified - screen doesn't have direct theatre reference
        dto.setScreenName(show.getScreen().getName());
        dto.setStartTime(show.getStartTime());
        dto.setEndTime(show.getEndTime());
        dto.setFeatures(show.getFeatures());
        
        // Get available seats
        List<ShowSeat> availableShowSeats = showSeatRepo.findByShowIdAndShowSeatStatus(
                show.getId(), ShowSeatStatus.AVAILABLE);
        
        dto.setAvailableSeats(availableShowSeats.stream()
                .map(this::convertSeatToResponseDTO)
                .collect(Collectors.toList()));
        
        return dto;
    }
    
    private SeatResponseDTO convertSeatToResponseDTO(ShowSeat showSeat) {
        SeatResponseDTO dto = new SeatResponseDTO();
        dto.setId(showSeat.getSeat().getId());
        dto.setSeatNo(showSeat.getSeat().getSeatNo());
        dto.setType(showSeat.getSeat().getType());
        dto.setRowVal(showSeat.getSeat().getRowVal());
        dto.setColVal(showSeat.getSeat().getColVal());
        dto.setStatus(showSeat.getShowSeatStatus());
        return dto;
    }
}
