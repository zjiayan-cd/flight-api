package com.example.flightapi.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Map<String, Object> searchFlights(@RequestBody FlightSearchRequest request) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	DateTimeFormatter formatterRes = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	LocalDate departDate = LocalDate.parse(request.getDepartDate().substring(0, 10), formatter);
 
    	System.out.println("*****" + request.getDepartDate());
    	
        // 基础过滤逻辑
    	/*
        List<Flight> flights = flightRepository.findAll().stream()
            .filter(f -> f.getDeparture().getCode().equalsIgnoreCase(request.getFrom()))
            .filter(f -> f.getArrival().getCode().equalsIgnoreCase(request.getTo()))
            .filter(f -> {
            	LocalDateTime departureTime  = f.getDepartureTime();
            	return (departureTime.isAfter(now) && departureTime.toLocalDate().isEqual(LocalDate.parse(request.getDepartDate().substring(0, 10), formatter)));
            })
            .collect(Collectors.toList());

        return flights.stream()
            .map(flight -> new FlightResponse(
                flight.getId(),
                flight.getFlightNumber(),
                flight.getDeparture().getCity(),
                flight.getArrival().getCity(),
                flight.getDepartureTime().format(formatterRes),
                flight.getArrivalTime().format(formatterRes),
                flight.getPrice().doubleValue()
            ))
            .collect(Collectors.toList());
           */
    	
        
        Pageable pageable = PageRequest.of(
        		request.getPage(), 
        		request.getSize(), 
        		Sort.by(Sort.Direction.fromString(request.getSortOrder()), request.getSortBy())
        );
        
        Page<Flight> pageResult  = flightRepository.findBySearchParams(
                request.getFrom(),
                request.getTo(),
                departDate.atStartOfDay(),
                departDate.plusDays(1).atStartOfDay(),
                pageable
            );
       
        
        List<FlightResponse> flightResponses = pageResult.getContent().stream()
                .map(flight -> new FlightResponse(
                    flight.getId(),
                    flight.getFlightNumber(),
                    flight.getDeparture().getCity(),
                    flight.getArrival().getCity(),
                    flight.getDepartureTime().format(formatterRes),
                    flight.getArrivalTime().format(formatterRes),
                    flight.getPrice().doubleValue()
                ))
                .collect(Collectors.toList());
        
        // 返回分页结构
        Map<String, Object> result = new HashMap<>();
        result.put("flights", flightResponses);
        result.put("total", pageResult.getTotalPages());
        return result;
    }
}
