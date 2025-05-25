package com.example.scheduleapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {
    @NotBlank(message = "할일은 필수입니다.")
    private String task;

    @NotBlank(message = "작성자명은 필수입니다.")
    private String writer;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}