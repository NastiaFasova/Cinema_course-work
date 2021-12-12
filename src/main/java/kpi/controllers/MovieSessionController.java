package kpi.controllers;

import kpi.models.MovieSession;
import kpi.models.dto.request.MovieSessionRequestDto;
import kpi.models.dto.response.MovieSessionResponseDto;
import kpi.models.mapper.MovieSessionMapper;
import kpi.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/movie-sessions")
public class MovieSessionController {

    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper movieSessionMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapper;
    }

    @PostMapping
    public void add(@RequestBody @Valid MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(movieSessionMapper.getMovieSession(movieSessionRequestDto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAll(@RequestParam (name = "movieId") Long id,
                                                @RequestParam (name = "date") LocalDate showTime) {
        List<MovieSession> movieSessions = movieSessionService.getAvailableSessions(id, showTime);
        return movieSessions.stream()
                .map(movieSessionMapper::getMovieSessionResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<MovieSessionResponseDto> getAll() {
        List<MovieSession> movieSessions = movieSessionService.getAll();
        return movieSessions.stream()
                .map(movieSessionMapper::getMovieSessionResponseDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return movieSessionService.deleteById(id);
    }
}
