package com.example.book_my_show.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.book_my_show.dto.request.PaymentRequestDTO;
import com.example.book_my_show.dto.response.PaymentResponseDTO;
import com.example.book_my_show.model.*;
import com.example.book_my_show.repo.*;

@Service
@RequiredArgsConstructor
public class PaymentService {
    
    private final PaymentRepo paymentRepo;
    private final BookingRepo bookingRepo;
    
    public List<PaymentResponseDTO> getAllPayments() {
        return paymentRepo.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    public PaymentResponseDTO getPaymentById(Long id) {
        Payment payment = paymentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        return convertToResponseDTO(payment);
    }
    
    public PaymentResponseDTO getPaymentByBookingId(Long bookingId) {
        Payment payment = paymentRepo.findByBookingId(bookingId)
                .orElseThrow(() -> new RuntimeException("Payment not found for booking id: " + bookingId));
        return convertToResponseDTO(payment);
    }
    
    @Transactional
    public PaymentResponseDTO processPayment(PaymentRequestDTO request) {
        // Validate booking
        Booking booking = bookingRepo.findById(request.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        
        // Check if booking is confirmed and payment hasn't been made
        if (booking.getBookingStatus() != BookingStatus.CONFIRMED) {
            throw new RuntimeException("Booking is not confirmed");
        }
        
        // Check if payment already exists
        if (paymentRepo.existsByBookingId(request.getBookingId())) {
            throw new RuntimeException("Payment already exists for this booking");
        }
        
        // Validate amount
        if (!request.getAmount().equals(booking.getTotalAmount())) {
            throw new RuntimeException("Payment amount doesn't match booking amount");
        }
        
        // Create payment
        Payment payment = new Payment();
        payment.setRefNo(generatePaymentRefNo());
        payment.setAmount(request.getAmount());
        payment.setPaymentStatus(PaymentStatus.SUCCESS); // Simplified - in real app, integrate with payment gateway
        payment.setPaymentTime(new Date());
        payment.setBooking(booking);
        
        payment = paymentRepo.save(payment);
        
        // Update booking with payment reference
        booking.setPayment(payment);
        bookingRepo.save(booking);
        
        return convertToResponseDTO(payment);
    }
    
    @Transactional
    public PaymentResponseDTO refundPayment(Long paymentId) {
        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        
        if (payment.getPaymentStatus() != PaymentStatus.SUCCESS) {
            throw new RuntimeException("Payment cannot be refunded - current status: " + payment.getPaymentStatus());
        }
        
        // Process refund (simplified)
        payment.setPaymentStatus(PaymentStatus.REFUNDED);
        payment = paymentRepo.save(payment);
        
        // Update booking status
        Booking booking = payment.getBooking();
        booking.setBookingStatus(BookingStatus.CANCELLED);
        bookingRepo.save(booking);
        
        return convertToResponseDTO(payment);
    }
    
    private String generatePaymentRefNo() {
        return "PAY_" + UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
    }
    
    private PaymentResponseDTO convertToResponseDTO(Payment payment) {
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setId(payment.getId());
        dto.setRefNo(payment.getRefNo());
        dto.setAmount(payment.getAmount());
        dto.setStatus(payment.getPaymentStatus());
        dto.setPaymentTime(payment.getPaymentTime());
        dto.setBookingId(payment.getBooking().getId());
        return dto;
    }
}
