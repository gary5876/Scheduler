package com.example.scheduleapp.exception;

public class PasswordNotMatchException extends RuntimeException {
    public PasswordNotMatchException() {
        super("비밀번호가 일치하지 않습니다.");
    }
}
