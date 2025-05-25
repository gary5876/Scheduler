package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.*;
import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(ScheduleRequestDto dto);
    List<ScheduleResponseDto> getSchedules(String writer, String date);
    ScheduleResponseDto getSchedule(Long id);
    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto);
    void deleteSchedule(Long id, ScheduleDeleteRequestDto dto);
}