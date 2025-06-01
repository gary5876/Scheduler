package com.example.scheduleapp.exception;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException(Long id) {
        super("일정 ID " + id + "에 해당하는 일정을 찾을 수 없습니다.");
    }
}