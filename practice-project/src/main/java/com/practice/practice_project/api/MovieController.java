package com.practice.practice_project.api;

import com.practice.practice_project.common.statics.Endpoints;
import com.practice.practice_project.dto.RestResponse;
import com.practice.practice_project.dto.request.MovieRequest;
import com.practice.practice_project.dto.response.MovieResponse;
import com.practice.practice_project.service.business.MovieService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(Endpoints.MOVIE_API)
@RestController
@RequiredArgsConstructor
@Validated
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/create")
    public ResponseEntity<RestResponse<MovieRequest>> saveMovie(@RequestBody @Valid MovieRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.saveMovie(request));
    }
    @PutMapping("/rate")
    public ResponseEntity<RestResponse<String>> setRate(
            @RequestParam Integer rate,
            @RequestParam Long movieId){
        return ResponseEntity.accepted().body(movieService.setRate(rate, movieId));
    }
    @GetMapping("/find-movie")
    public ResponseEntity<RestResponse<MovieResponse>> getMovieByName(
            @RequestParam
            @NotNull(message = "Имя фильма не может быть пустым")
            String name) {
        return ResponseEntity.ok(movieService.findMovieByName(name));
    }
    @GetMapping(value = "/find-movies")
    public ResponseEntity<RestResponse<List<MovieResponse>>> getOrderedMoviesByKeyword(
            @RequestParam @NotNull(message = "Ключ для поиска фильмов не может быть пустым") String keyword){
        return ResponseEntity.ok(movieService.findMoviesByKeywordOrderByCreationDate(keyword));
    }
}
