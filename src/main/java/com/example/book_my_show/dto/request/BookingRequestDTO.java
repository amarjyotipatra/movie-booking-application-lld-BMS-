package com.example.book_my_show.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class BookingRequestDTO {
    private Long userId;
    private Long showId;
    private List<Long> seatIds;
}
