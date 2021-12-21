package kpi.service.impl;

import kpi.repository.MovieRepository;
import kpi.models.Movie;
import kpi.service.MovieService;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie add(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie add(Movie movie, String id) {
        Movie current = get(Long.parseLong(id));
        current.setTitle(movie.getTitle());
        current.setLink(movie.getLink());
        current.setApiId(movie.getApiId());
        return movieRepository.save(current);
    }

    @Override
    public Movie get(long id) {
        return movieRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public boolean deleteById(Long id) {
        movieRepository.deleteById(id);
        return true;
    }
}
