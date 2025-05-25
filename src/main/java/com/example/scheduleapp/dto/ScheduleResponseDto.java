package com.example.scheduleapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String task;
    private String writer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}