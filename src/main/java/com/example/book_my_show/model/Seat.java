package com.example.book_my_show.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel {
    private String seatNo;
    @ManyToOne
    private SeatType type;
    private int rowVal;
    private int colVal;
}
