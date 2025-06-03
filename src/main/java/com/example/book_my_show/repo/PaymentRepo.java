package com.example.book_my_show.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import com.example.book_my_show.model.Payment;
import com.example.book_my_show.model.PaymentStatus;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
    
    Optional<Payment> findByRefNo(String refNo);
    Optional<Payment> findByBookingId(Long bookingId);
    boolean existsByBookingId(Long bookingId);
    List<Payment> findByPaymentStatus(PaymentStatus status);
    
}
