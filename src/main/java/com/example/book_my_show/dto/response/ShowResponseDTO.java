package com.example.book_my_show.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import com.example.book_my_show.model.Feature;

@Getter
@Setter
public class ShowResponseDTO {
    private Long id;
    private String movieName;
    private String theatreName;
    private String screenName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Feature> features;
    private List<SeatResponseDTO> availableSeats;
}
