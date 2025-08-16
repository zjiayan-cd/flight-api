package com.example.flightapi.repository;

import com.example.flightapi.entity.Flight;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDeparture_CodeAndArrival_Code(String departure, String destination);
    
    @Query("SELECT f FROM Flight f " +
           "WHERE f.departure.code = :from " +
           "AND f.arrival.code = :to " +
           "AND f.departureTime >= :start " +
           "AND f.departureTime < :end")
    Page<Flight> findBySearchParams(
        @Param("from") String from,
        @Param("to") String to,
        @Param("start") LocalDateTime start,
        @Param("end") LocalDateTime end,
        Pageable pageable
    );

}
