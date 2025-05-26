package com.example.flightapi.controller;

import com.example.flightapi.dto.AirportDto;
import com.example.flightapi.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public List<AirportDto> getAllAirports() {
        return airportService.getAllAirports();
    }
}
