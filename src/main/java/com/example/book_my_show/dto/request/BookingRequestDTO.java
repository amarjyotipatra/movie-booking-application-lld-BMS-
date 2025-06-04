package com.example.book_my_show.dto.request;

import lombok.Getter;
import lombok.Setter;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class BookingRequestDTO {
    @Nonnull(message = "User ID cannot be null")
    private Long userId;
    
    @NotNull(message = "Show ID cannot be null")
    private Long showId;
    
    @NotNull(message = "Seat IDs cannot be null")
    @Size(min = 1, message = "At least one seat must be selected")
    private List<Long> seatIds;
}
