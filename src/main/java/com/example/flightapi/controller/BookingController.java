package com.example.flightapi.controller;

import com.example.flightapi.common.BaseResponse;
import com.example.flightapi.dto.BookingDTO;
import com.example.flightapi.dto.BookingRequest;
import com.example.flightapi.entity.Booking;
import com.example.flightapi.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@Tag(name = "订单管理", description = "用户订单相关接口")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    
    @Operation(summary = "获取用户订单", description = "根据当前登录用户，查询其所有订单")
    @GetMapping
    public ResponseEntity<BaseResponse<List<BookingDTO>>> getUserBookings(Authentication authentication) {
        String username = authentication.getName(); // 从 JWT 中提取用户名
        List<BookingDTO> bookings = bookingService.getBookingsByUsername(username);
        return ResponseEntity.ok(BaseResponse.success(HttpStatus.OK, bookings));
    }

    @Operation(summary = "创建订单", description = "根据请求创建一个或多个订单")
    @PostMapping
    public ResponseEntity<BaseResponse<List<Booking>>> createBooking(@RequestBody BookingRequest bookingRequest) {
        List<Booking> bookings = bookingService.createBookings(bookingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.success(HttpStatus.CREATED, bookings));
    }
}
