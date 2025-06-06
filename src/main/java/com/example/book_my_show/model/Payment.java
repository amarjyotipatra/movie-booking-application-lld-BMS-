package com.example.book_my_show.model;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {
      private String refNo; // ThirdParty refNo.
    private Double amount;
    private Date paymentTime;
    private String paymentMethod; // CREDIT_CARD, DEBIT_CARD, UPI, NET_BANKING
    
    @OneToOne
    private Booking booking;
    
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;
}
