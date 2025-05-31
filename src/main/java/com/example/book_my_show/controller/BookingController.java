package com.example.book_my_show.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.example.book_my_show.dto.request.BookingRequestDTO;
import com.example.book_my_show.dto.response.BookingResponseDTO;
import com.example.book_my_show.service.BookingService;

@RestController
@RequestMapping("/api/v1")
public class BookingController {
    
    private BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }    @GetMapping("/bookings")
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        List<BookingResponseDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @PostMapping("/bookings")
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingRequestDTO bookingRequestDTO) {
        BookingResponseDTO booking = bookingService.createBooking(bookingRequestDTO);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long bookingId) {
        BookingResponseDTO booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingResponseDTO> updateBooking(@PathVariable Long bookingId, @RequestBody BookingRequestDTO bookingRequestDTO) {
        BookingResponseDTO booking = bookingService.updateBooking(bookingId, bookingRequestDTO);
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok().build();
    }
}
