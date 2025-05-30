package com.example.flightapi.service;

import com.example.flightapi.dto.BookingRequest;
import com.example.flightapi.dto.BookingDTO;
import com.example.flightapi.dto.BookingItemDTO;
import com.example.flightapi.dto.PassengerDTO;
import com.example.flightapi.dto.FlightResponse;
import com.example.flightapi.entity.Booking;
import com.example.flightapi.entity.Flight;
import com.example.flightapi.entity.Passenger;
import com.example.flightapi.entity.User;
import com.example.flightapi.repository.BookingRepository;
import com.example.flightapi.repository.FlightRepository;
import com.example.flightapi.repository.PassengerRepository;
import com.example.flightapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;

    public BookingService(UserRepository userRepository, FlightRepository flightRepository,
                          BookingRepository bookingRepository, PassengerRepository passengerRepository) {
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
        this.passengerRepository = passengerRepository;
    }
    
    public List<BookingDTO> getBookingsByUsername(String username) {
    	List<Booking> bookings = bookingRepository.findByUserUsername(username);
    	return bookings.stream()
                .map(booking -> new BookingDTO(
                		booking.getReference(),
                		booking.getStatus(),
                		new FlightResponse(
                                booking.getFlight().getId(),
                                booking.getFlight().getFlightNumber(),
                                booking.getFlight().getDeparture().getCode(),
                                booking.getFlight().getArrival().getCode(),
                                booking.getFlight().getDepartureTime().toString(),
                                booking.getFlight().getArrivalTime().toString(),
                                booking.getFlight().getPrice().doubleValue()
                            )
                		)
                	)
                    .toList();
    }


    @Transactional
    public List<Booking> createBookings(BookingRequest bookingRequest) {
    	System.out.println("userName:"+bookingRequest.getUsername());
        User user = userRepository.findByUsername(bookingRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Booking> savedBookings = new ArrayList<>();

        for (BookingItemDTO bookingItem : bookingRequest.getBookings()) {
            Flight flight = flightRepository.findById(bookingItem.getFlightId())
                    .orElseThrow(() -> new RuntimeException("Flight not found: " + bookingItem.getFlightId()));

            // 计算总价（简单示例，实际可更复杂）
            BigDecimal basePrice = flight.getPrice();
            BigDecimal tax = basePrice.multiply(BigDecimal.valueOf(0.1));
            BigDecimal totalPricePerPassenger = basePrice.add(tax);
            BigDecimal totalPrice = totalPricePerPassenger.multiply(BigDecimal.valueOf(bookingItem.getPassengers().size()));

            Booking booking = Booking.builder()
                    .user(user)
                    .flight(flight)
                    .bookingTime(LocalDateTime.now())
                    .status("BOOKED")
                    .totalPrice(totalPrice)
                    .reference(generateReference())
                    .build();

            Booking savedBooking = bookingRepository.save(booking);

            // 保存乘客
            List<Passenger> passengers = new ArrayList<>();
            for (PassengerDTO p : bookingItem.getPassengers()) {
                Passenger passenger = Passenger.builder()
                        .name(p.getName())
                        .idNumber(p.getIdNumber())
                        .booking(savedBooking)
                        .build();
                passengers.add(passenger);
            }
            passengerRepository.saveAll(passengers);

            savedBookings.add(savedBooking);
        }

        return savedBookings;
    }

    private String generateReference() {
        // 简单用UUID的前8位
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
