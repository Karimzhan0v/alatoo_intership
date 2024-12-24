package com.practice.practice_project.dto.response;

import com.practice.practice_project.common.enums.MovieCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class MovieResponse {
    private String name;
    private MovieCategory category;
    private Integer rate;
}
