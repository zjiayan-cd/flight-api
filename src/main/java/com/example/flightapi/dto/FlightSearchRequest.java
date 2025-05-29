package com.example.flightapi.dto;

import lombok.Data;

@Data
public class FlightSearchRequest {
    private String tripType; // oneway 或 roundtrip
    private String from;
    private String to;
    private String departDate;
    private String returnDate; // roundtrip 才用到
    private int passengers;
}
