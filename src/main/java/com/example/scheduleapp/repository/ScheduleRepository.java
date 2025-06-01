package com.example.scheduleapp.repository;

import com.example.scheduleapp.dto.ScheduleResponseDto;
import com.example.scheduleapp.model.Schedule;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Schedule save(Schedule schedule);
    List<Schedule> findAll(String writer, String date);
    Optional<Schedule> findById(Long id);
    boolean update(Long id, Schedule schedule);
    boolean delete(Long id);
    boolean checkPassword(Long id, String password);
    List<ScheduleResponseDto> findAllPaged(int page, int size);
}