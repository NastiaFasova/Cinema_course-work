package kpi.controllers;

import kpi.models.Movie;
import kpi.models.dto.MovieDto;
import kpi.models.mapper.MovieMapper;
import kpi.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
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
    public MovieDto add(@RequestBody @Valid MovieDto movieRequestDto) {
        return movieMapper.getMovieResponseDto(movieService.add(movieMapper.getMovie(movieRequestDto)));
    }

//    @GetMapping
//    public List<MovieDto> getAll() {
//        List<Movie> movies = movieService.getAll();
//        return movies.stream()
//                .map(movieMapper::getMovieResponseDto)
//                .collect(Collectors.toList());
//    }

    @GetMapping
    public List<Movie> viewMovies(@RequestParam(required = false) String keyword,
                            @RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "7") int size,
                            @RequestParam(value = "sortField", defaultValue = "title") String sortField,
                            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        Page<Movie> pageTuts = movieService.findAllPaginated(keyword, page, size, sortField, sortDir);
        return pageTuts.getContent();
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return movieService.deleteById(id);
    }
}
