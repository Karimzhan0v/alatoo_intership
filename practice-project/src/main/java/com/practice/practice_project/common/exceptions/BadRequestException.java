package com.practice.practice_project.common.exceptions;

import com.practice.practice_project.common.enums.SystemCodes;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BadRequestException extends RuntimeException{
    private final SystemCodes systemCodes;
}
