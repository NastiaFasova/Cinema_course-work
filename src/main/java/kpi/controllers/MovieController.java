package kpi.controllers;

import kpi.models.Movie;
import kpi.models.dto.MovieDto;
import kpi.models.mapper.MovieMapper;
import kpi.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping
    public @ResponseBody MovieDto add(@RequestBody @Valid MovieDto movieRequestDto) {
        return movieMapper.getMovieResponseDto(movieService.add(movieMapper.getMovie(movieRequestDto)));
    }

    @PatchMapping("/{id}")
    public @ResponseBody Movie update(@RequestBody @Validated MovieDto movieDto, @PathVariable("id") String id) {
        return movieService.add(movieMapper.getMovie(movieDto), id);
    }

    @GetMapping
    public @ResponseBody List<MovieDto> getAll() {
        List<Movie> movies = movieService.getAll();
        return movies.stream()
                .map(movieMapper::getMovieResponseDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return movieService.deleteById(id);
    }
}
