package com.example.flightapi.service;

import com.example.flightapi.dto.AirportDto;
import com.example.flightapi.entity.Airport;
import com.example.flightapi.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    public List<AirportDto> getAllAirports() {
        return airportRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private AirportDto convertToDto(Airport airport) {
        AirportDto dto = new AirportDto();
        dto.setId(airport.getId());
        dto.setCode(airport.getCode());
        dto.setName(airport.getName());
        dto.setCity(airport.getCity());
        dto.setCountry(airport.getCountry());
        return dto;
    }
}
