package com.example.flightapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookingItemDTO {
    private Long flightId;
    private List<PassengerDTO> passengers;
}
