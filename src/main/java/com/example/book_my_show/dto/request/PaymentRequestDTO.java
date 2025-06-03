package com.example.book_my_show.dto.request;

import lombok.Getter;
import lombok.Setter;
import com.example.book_my_show.model.PaymentStatus;

@Getter
@Setter
public class PaymentRequestDTO {
    private Long bookingId;
    private Double amount;
    private String paymentMethod; // CREDIT_CARD, DEBIT_CARD, UPI, NET_BANKING
    private String cardNumber;
    private String cardHolderName;
}
