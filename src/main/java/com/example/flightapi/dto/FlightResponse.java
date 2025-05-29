package com.example.flightapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlightResponse {
    private Long id;
    private String flightNumber;
    private String airlineLogo;
    private String departure;
    private String arrival;
    private String departureTime;
    private String arrivalTime;
//    private String duration;
//    private int stops;
    private double price;
}
