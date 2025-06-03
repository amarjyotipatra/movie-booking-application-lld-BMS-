package com.example.book_my_show.dto.response;

import lombok.Getter;
import lombok.Setter;
import com.example.book_my_show.model.SeatType;
import com.example.book_my_show.model.ShowSeatStatus;

@Getter
@Setter
public class SeatResponseDTO {
    private Long id;
    private String seatNo;
    private SeatType type;
    private int rowVal;
    private int colVal;
    private ShowSeatStatus status;
    private Double price;
}
