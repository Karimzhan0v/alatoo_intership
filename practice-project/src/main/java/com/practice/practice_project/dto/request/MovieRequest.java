package com.practice.practice_project.dto.request;

import com.practice.practice_project.common.enums.MovieCategory;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieRequest {
    @NotNull(message = "Имя фильма не может быть пустым")
    private String name;
    @NotNull(message = "Категория фильма не может быть пустым")
    private MovieCategory category;
    private Integer rate;
    @NotNull(message = "ID клиента не может быть пустым")
    private Long clientId;
}
