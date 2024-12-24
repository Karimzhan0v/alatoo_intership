package com.practice.practice_project.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SystemCodes {
    ENTITY_NOT_FOUND(0, "Запись не найдена", HttpStatus.NOT_FOUND),
    SUCCESS(1, "Успех!", HttpStatus.OK),
    NO_DATA(2, "Данные по предоставленным аргументам не найдены", HttpStatus.NOT_FOUND),
    CLIENT_NOT_FOUND(3, "Клиент с предоставленным ID не найден", HttpStatus.BAD_REQUEST);
    private final Integer code;
    private final String message;
    private final HttpStatus status;
}
