package com.example.book_my_show.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {
    
    private String refNo; // ThirdParty refNo.
    
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;
}
