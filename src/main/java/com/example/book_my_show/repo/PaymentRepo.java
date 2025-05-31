package com.example.book_my_show.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_my_show.model.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
    
    // Additional query methods can be defined here if needed
    
}
