package com.example.book_my_show.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel {
    private String seatNo;
    @Enumerated(EnumType.STRING)
    private SeatType type;
    private int rowVal;
    private int colVal;
}
