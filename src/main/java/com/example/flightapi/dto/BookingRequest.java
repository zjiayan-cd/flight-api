package com.example.flightapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookingRequest {
    private Long userId;
    private List<BookingItemDTO> bookings;
}
