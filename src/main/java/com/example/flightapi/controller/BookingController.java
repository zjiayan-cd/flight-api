package com.example.flightapi.controller;

import com.example.flightapi.dto.BookingDTO;
import com.example.flightapi.dto.BookingRequest;
import com.example.flightapi.entity.Booking;
import com.example.flightapi.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    
    @GetMapping
    public ResponseEntity<List<BookingDTO>> getUserBookings(Authentication authentication) {
        String username = authentication.getName(); // 从 JWT 中提取用户名
        List<BookingDTO> bookings = bookingService.getBookingsByUsername(username);
        return ResponseEntity.ok(bookings);
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest bookingRequest) {
        try {
            List<Booking> bookings = bookingService.createBookings(bookingRequest);
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
