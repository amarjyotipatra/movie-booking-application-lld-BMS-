package com.example.book_my_show.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.book_my_show.dto.request.BookingRequestDTO;
import com.example.book_my_show.dto.response.BookingResponseDTO;
import com.example.book_my_show.service.BookingService;

public class BookingController { 
    
    private BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings")
    public BookingResponseDTO getAllBookings(BookingRequestDTO bookingRequestDTO) {
        // Logic to book tickets
        return bookingService.bookTickets(bookingRequestDTO);
    }

    @PostMapping("/bookings/{bookingId}")
    public BookingResponseDTO bookTickets(BookingRequestDTO bookingRequestDTO,@PathVariable Long bookingId) {
        // Logic to book tickets
        return bookingService.bookTickets(bookingRequestDTO);
    }

    @PutMapping("/bookings/{bookingId}")
    public BookingResponseDTO updateBooking(Long bookingId, BookingRequestDTO bookingRequestDTO) {
        // Logic to update a booking
        return bookingService.updateBooking(bookingId, bookingRequestDTO);
    }
}
