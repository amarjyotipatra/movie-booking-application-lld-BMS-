package com.example.book_my_show.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.book_my_show.dto.request.BookingRequestDTO;
import com.example.book_my_show.dto.response.BookingResponseDTO;
import com.example.book_my_show.model.*;
import com.example.book_my_show.repo.*;

@Service
@RequiredArgsConstructor
public class BookingService {
    
    private final BookingRepo bookingRepo;
    private final UserRepo userRepo;
    private final ShowRepo showRepo;
    private final SeatRepo seatRepo;
    private final ShowSeatRepo showSeatRepo;
    private final PaymentRepo paymentRepo;
    
    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepo.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    public BookingResponseDTO getBookingById(Long bookingId) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
        return convertToResponseDTO(booking);
    }
    
    @Transactional
    public BookingResponseDTO createBooking(BookingRequestDTO request) {
        // Validate user
        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Validate show
        Show show = showRepo.findById(request.getShowId())
                .orElseThrow(() -> new RuntimeException("Show not found"));
        
        // Validate and reserve seats
        List<ShowSeat> showSeats = request.getSeatIds().stream()
                .map(seatId -> {
                    ShowSeat showSeat = showSeatRepo.findByShowIdAndSeatId(request.getShowId(), seatId)
                            .orElseThrow(() -> new RuntimeException("Seat not available for this show"));
                    
                    if (showSeat.getShowSeatStatus() != ShowSeatStatus.AVAILABLE) {
                        throw new RuntimeException("Seat " + showSeat.getSeat().getSeatNo() + " is not available");
                    }
                    
                    // Reserve the seat
                    showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
                    return showSeatRepo.save(showSeat);
                })
                .collect(Collectors.toList());
        
        // Calculate total amount (simple pricing logic)
        double totalAmount = showSeats.stream()
                .mapToDouble(seat -> getPriceForSeatType(seat.getSeat().getType()))
                .sum();
        
        // Create booking
        Booking booking = new Booking();        booking.setUserCreatedBy(user);
        booking.setShow(show);
        booking.setShowSeats(showSeats);
        booking.setTotalAmount(totalAmount);
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        booking.setBookingCreatedAt(new Date());
        
        booking = bookingRepo.save(booking);
        
        // Mark seats as booked
        showSeats.forEach(seat -> {
            seat.setShowSeatStatus(ShowSeatStatus.BOOKED);
            showSeatRepo.save(seat);
        });
        
        return convertToResponseDTO(booking);
    }
    
    @Transactional
    public BookingResponseDTO updateBooking(Long bookingId, BookingRequestDTO request) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        
        // Update booking status based on request or business logic
        // For now, just return the existing booking
        return convertToResponseDTO(booking);
    }
    
    @Transactional
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        
        // Mark seats as available
        booking.getShowSeats().forEach(seat -> {
            seat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
            showSeatRepo.save(seat);
        });
        
        // Update booking status
        booking.setBookingStatus(BookingStatus.CANCELLED);
        bookingRepo.save(booking);
    }
    
    private BookingResponseDTO convertToResponseDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(booking.getId());
        dto.setUserId(booking.getUserCreatedBy().getId());
        dto.setUserName(booking.getUserCreatedBy().getUserName());
        dto.setShowId(booking.getShow().getId());
        dto.setMovieName(booking.getShow().getMovie().getName());
        dto.setTheatreName(booking.getShow().getScreen().getTheatre().getName());
        dto.setScreenName(booking.getShow().getScreen().getName());
        dto.setShowTime(booking.getShow().getStartTime());
        dto.setSeatNumbers(booking.getShowSeats().stream()
                .map(seat -> seat.getSeat().getSeatNo())
                .collect(Collectors.toList()));
        dto.setTotalAmount(booking.getTotalAmount());
        dto.setStatus(booking.getBookingStatus());
        dto.setBookingCreatedAt(booking.getBookingCreatedAt());
        if (booking.getPayment() != null) {
            dto.setPaymentRefNo(booking.getPayment().getRefNo());
        }
        return dto;
    }
    
    private double getPriceForSeatType(SeatType seatType) {
        switch (seatType) {
            case PLATINUM: return 300.0;
            case GOLD: return 200.0;
            case SILVER: return 150.0;
            default: return 100.0;
        }
    }
}
