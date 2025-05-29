package com.example.flightapi.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightapi.dto.FlightResponse;
import com.example.flightapi.dto.FlightSearchRequest;
import com.example.flightapi.entity.Flight;
import com.example.flightapi.repository.FlightRepository;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @PostMapping("/search")
    public List<FlightResponse> searchFlights(@RequestBody FlightSearchRequest request) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	
        // 示例：基础过滤逻辑
        List<Flight> flights = flightRepository.findAll().stream()
            .filter(f -> f.getDepartureAirport().getCode().equalsIgnoreCase(request.getFrom()))
            .filter(f -> f.getDestinationAirport().getCode().equalsIgnoreCase(request.getTo()))
            .collect(Collectors.toList());

        return flights.stream()
            .map(flight -> new FlightResponse(
                flight.getId(),
                flight.getFlightNumber(),
                "https://via.placeholder.com/40", // 模拟 logo
                flight.getDepartureAirport().getCity(),
                flight.getDestinationAirport().getCity(),
                flight.getDepartureTime().format(formatter),
                flight.getArrivalTime().format(formatter),
                flight.getPrice().doubleValue()
            ))
            .collect(Collectors.toList());
    }
}
