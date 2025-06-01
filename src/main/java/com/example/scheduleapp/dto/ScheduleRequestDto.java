package com.example.scheduleapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {
    @NotBlank(message = "할 일은 필수입니다.")
    @Size(max = 200, message = "할 일은 200자 이내여야 합니다.")
    private String task;

    @NotBlank(message = "작성자명은 필수입니다.")
    private String writer;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 비어 있을 수 없습니다.")
    private String email;

}