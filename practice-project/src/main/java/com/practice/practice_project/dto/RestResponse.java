package com.practice.practice_project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.practice.practice_project.common.enums.SystemCodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse <T>{
    private Integer code;
    private String message;
    private T data;
    private LocalDateTime responseTime;

    public static <T> RestResponse<T> getResponse(T data, SystemCodes systemCodes){
        return RestResponse.<T>builder()
                .code(systemCodes.getCode())
                .message(systemCodes.getMessage())
                .data(data)
                .responseTime(LocalDateTime.now())
                .build();
    }
}
