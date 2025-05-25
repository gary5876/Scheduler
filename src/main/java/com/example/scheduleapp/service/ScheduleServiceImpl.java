package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.*;
import com.example.scheduleapp.model.Schedule;
import com.example.scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {
        LocalDateTime now = LocalDateTime.now();
        Schedule schedule = new Schedule(
                null,
                dto.getTask(),
                dto.getWriter(),
                dto.getPassword(),
                now,
                now
        );
        Schedule saved = scheduleRepository.save(schedule);
        return toResponseDto(saved);
    }

    @Override
    public List<ScheduleResponseDto> getSchedules(String writer, String date) {
        return scheduleRepository.findAll(writer, date)
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));
        return toResponseDto(schedule);
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto) {
        if (!scheduleRepository.checkPassword(id, dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Schedule updated = new Schedule(
                id,
                dto.getTask(),
                dto.getWriter(),
                dto.getPassword(),
                null,
                LocalDateTime.now()
        );
        boolean result = scheduleRepository.update(id, updated);
        if (!result) throw new RuntimeException("수정에 실패했습니다.");
        return getSchedule(id);
    }

    @Override
    public void deleteSchedule(Long id, ScheduleDeleteRequestDto dto) {
        if (!scheduleRepository.checkPassword(id, dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        boolean result = scheduleRepository.delete(id);
        if (!result) throw new RuntimeException("삭제에 실패했습니다.");
    }

    private ScheduleResponseDto toResponseDto(Schedule schedule) {
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTask(),
                schedule.getWriter(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }
}
