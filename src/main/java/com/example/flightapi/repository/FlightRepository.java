package com.example.flightapi.repository;

import com.example.flightapi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDeparture_CodeAndArrival_Code(String departure, String destination);
}
