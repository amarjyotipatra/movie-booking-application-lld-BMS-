package com.example.book_my_show.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus; // Integer value of enum.
    
    @ManyToOne
    private User userCreatedBy;
    @ManyToOne
    private Show show;
    @OneToMany
    private List<ShowSeat>  showSeats;
    @OneToOne
    private Payment payment;

    private Date bookingCreatedAt;
    private Double totalAmount;
}
