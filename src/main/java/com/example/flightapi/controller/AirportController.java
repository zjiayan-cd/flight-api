package com.example.flightapi.controller;

import com.example.flightapi.common.BaseResponse;
import com.example.flightapi.dto.AirportDto;
import com.example.flightapi.service.AirportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
@Tag(name = "机场管理", description = "提供机场数据查询接口")
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    @Operation(summary = "获取所有机场信息", description = "返回所有可用的机场数据列表")
    public ResponseEntity<BaseResponse<List<AirportDto>>> getAllAirports() {
    	List<AirportDto> airports = airportService.getAllAirports();
        return ResponseEntity.ok(BaseResponse.success(HttpStatus.OK, airports));
    }
}
