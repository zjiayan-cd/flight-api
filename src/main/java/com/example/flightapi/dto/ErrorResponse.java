package com.example.flightapi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private int status; // HTTP 状态码
    private String error; // 状态文本，如 "Bad Request"
    private String message; // 主错误信息
    private LocalDateTime timestamp;
}
