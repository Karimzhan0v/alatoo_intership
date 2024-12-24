package com.practice.practice_project.service.business;

import com.practice.practice_project.common.enums.MovieCategory;
import com.practice.practice_project.common.enums.SystemCodes;
import com.practice.practice_project.common.exceptions.BadRequestException;
import com.practice.practice_project.dto.RestResponse;
import com.practice.practice_project.dto.request.MovieRequest;
import com.practice.practice_project.dto.response.MovieResponse;
import com.practice.practice_project.model.ClientEntity;
import com.practice.practice_project.model.ClientRepository;
import com.practice.practice_project.model.MovieEntity;
import com.practice.practice_project.model.MovieRepository;
import com.practice.practice_project.service.db.MovieDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class MovieService {
    private final MovieDbService movieDbService;
    private final ClientRepository clientRepository;
    private final MovieRepository movieRepository;

    public RestResponse<MovieResponse> findMovieByName(String name) {
        MovieEntity movieEntity = movieDbService.findByName(name);
        MovieResponse movieResponse = MovieResponse.builder()
                .name(movieEntity.getName())
                .category(movieEntity.getCategory())
                .rate(movieEntity.getRate())
                .build();
        return RestResponse.getResponse(movieResponse, SystemCodes.SUCCESS);
    }

    public RestResponse<MovieRequest> saveMovie(MovieRequest request) {
        ClientEntity clientEntity = clientRepository.findById(request.getClientId()).orElseThrow(
                () -> new BadRequestException(SystemCodes.CLIENT_NOT_FOUND));
        movieDbService.saveEntity(request, clientEntity);
        return RestResponse.getResponse(request, SystemCodes.SUCCESS);
    }

    public RestResponse<List<MovieResponse>> findMoviesByKeywordOrderByCreationDate(String keyword) {
        List<MovieEntity> movies = movieDbService.findMoviesByKeywordOrderByCreationDate(keyword);
        if (movies.isEmpty()) {
            log.warn("MovieService.findMoviesByKeywordOrderByCreationDate({}) throws: {}", keyword,
                    SystemCodes.ENTITY_NOT_FOUND.getMessage());
            throw new BadRequestException(SystemCodes.NO_DATA);
        }
        List<MovieResponse> movieResponses = movies.stream()
                .map(movie -> {
                    String name = movie.getName();
                    MovieCategory category = movie.getCategory();
                    Integer rate = movie.getRate();
                    return MovieResponse.builder()
                            .name(name)
                            .rate(rate)
                            .category(category)
                            .build();
                })
                .toList();
        return RestResponse.getResponse(movieResponses, SystemCodes.SUCCESS);
    }

    public RestResponse<String> setRate(Integer rate, Long movieId) {
        MovieEntity entity = movieRepository.findById(movieId)
                .orElseThrow(() -> new BadRequestException(SystemCodes.ENTITY_NOT_FOUND));
        entity.setRate(rate);
        movieRepository.save(entity);
        return RestResponse.getResponse(null, SystemCodes.SUCCESS);
    }
}
