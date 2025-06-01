package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.*;
import com.example.scheduleapp.exception.*;
import com.example.scheduleapp.model.Schedule;
import com.example.scheduleapp.repository.ScheduleRepository;
import com.example.scheduleapp.repository.ScheduleRepositoryImpl;
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
                .orElseThrow(() -> new ScheduleNotFoundException(id));

        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTask(),
                schedule.getWriter(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));

        if (!schedule.getPassword().equals(dto.getPassword())) {
            throw new PasswordNotMatchException();
        }

        schedule.setTask(dto.getTask());
        schedule.setWriter(dto.getWriter());
        schedule.setUpdatedAt(LocalDateTime.now());

        boolean result = scheduleRepository.update(id, schedule);
        if (!result) throw new RuntimeException("수정에 실패했습니다.");
        return getSchedule(id);
    }

    @Override
    public void deleteSchedule(Long id, ScheduleDeleteRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));

        if (!schedule.getPassword().equals(dto.getPassword())) {
            throw new PasswordNotMatchException();
        }

        scheduleRepository.delete(id);
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

    public List<ScheduleResponseDto> getSchedulesPaged(int page, int size) {
        return scheduleRepository.findAllPaged(page, size);
    }

}
