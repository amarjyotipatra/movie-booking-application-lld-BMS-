package com.example.book_my_show.dto.response;

import lombok.Getter;
import lombok.Setter;
import com.example.book_my_show.model.BookingStatus;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BookingResponseDTO {
    private Long id;
    private Long userId;
    private String userName;
    private Long showId;
    private String movieName;
    private String theatreName;
    private String screenName;
    private Date showTime;
    private List<String> seatNumbers;
    private Double totalAmount;
    private BookingStatus status;
    private Date bookingCreatedAt;
    private String paymentRefNo;
}
