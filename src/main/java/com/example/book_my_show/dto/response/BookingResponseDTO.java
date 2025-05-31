package com.example.book_my_show.dto.response;

import lombok.Getter;
import lombok.Setter;
import com.example.book_my_show.model.BookingStatus;
import java.time.LocalDateTime;
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
    private LocalDateTime showTime;
    private List<String> seatNumbers;
    private Double totalAmount;
    private BookingStatus status;
    private LocalDateTime bookingCreatedAt;
    private String paymentRefNo;
}
