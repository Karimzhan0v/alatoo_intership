package com.practice.practice_project.service.db;

import com.practice.practice_project.common.enums.SystemCodes;
import com.practice.practice_project.common.exceptions.BadRequestException;
import com.practice.practice_project.dto.request.MovieRequest;
import com.practice.practice_project.model.ClientEntity;
import com.practice.practice_project.model.MovieEntity;
import com.practice.practice_project.model.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieDbService {
    private final MovieRepository movieRepository;

    public MovieEntity findByName(String name) {
        return movieRepository.findByName(name)
                .orElseThrow(() -> new BadRequestException(SystemCodes.ENTITY_NOT_FOUND));
    }

    public void saveEntity(MovieRequest request, ClientEntity client) {
        movieRepository.save(MovieEntity.builder()
                .name(request.getName())
                .category(request.getCategory())
                .rate(request.getRate())
                .client(client)
                .build());
    }

    public List<MovieEntity> findMoviesByKeywordOrderByCreationDate(String keyword) {
        return movieRepository.findByNameContainingIgnoreCaseOrderByCreationDateDesc(keyword);
    }
}
