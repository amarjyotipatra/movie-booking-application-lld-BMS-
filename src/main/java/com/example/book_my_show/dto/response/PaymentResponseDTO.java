package com.example.book_my_show.dto.response;

import lombok.Getter;
import lombok.Setter;
import com.example.book_my_show.model.PaymentStatus;
import java.util.Date;

@Getter
@Setter
public class PaymentResponseDTO {
    private Long id;
    private String refNo;
    private Double amount;
    private PaymentStatus status;
    private String paymentMethod;
    private Date paymentTime;
    private Long bookingId;
}
