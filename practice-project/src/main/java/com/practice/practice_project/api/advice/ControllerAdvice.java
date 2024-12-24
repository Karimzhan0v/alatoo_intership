package com.practice.practice_project.api.advice;

import com.practice.practice_project.common.exceptions.BadRequestException;
import com.practice.practice_project.common.statics.Constants;
import com.practice.practice_project.dto.RestResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RestResponse<String>> handleBadRequestException(BadRequestException e) {
        return ResponseEntity.status(e.getSystemCodes().getStatus())
                .body(RestResponse.<String>builder()
                        .code(e.getSystemCodes().getCode())
                        .message(e.getSystemCodes().getMessage())
                        .responseTime(LocalDateTime.now())
                        .build());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RestResponse<String>> handleConstraintViolation(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(RestResponse.<String>builder()
                        .code(Constants.DEFAULT_BAD_REQUEST_CODE)
                        .message(e.getMessage())
                        .responseTime(LocalDateTime.now())
                        .build());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse<String>> handleMethodArgument(MethodArgumentNotValidException e){
        List<String> errorMessage = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();
        String combinedMessage = String.join(", ", errorMessage);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(RestResponse.<String>builder()
                        .code(Constants.DEFAULT_BAD_REQUEST_CODE)
                        .message(combinedMessage)
                        .responseTime(LocalDateTime.now())
                        .build());
    }

}
