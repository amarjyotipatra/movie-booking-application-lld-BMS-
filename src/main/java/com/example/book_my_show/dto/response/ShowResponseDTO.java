package com.example.book_my_show.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import com.example.book_my_show.model.Feature;

@Getter
@Setter
public class ShowResponseDTO {
    private Long id;
    private String movieName;
    private String theatreName;
    private String screenName;
    private Date startTime;
    private Date endTime;
    private List<Feature> features;
    private List<SeatResponseDTO> availableSeats;
}
