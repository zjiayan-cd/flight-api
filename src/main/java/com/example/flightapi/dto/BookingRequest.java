package com.example.flightapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookingRequest {
    private String username;
    private List<BookingItemDTO> bookings;
}
