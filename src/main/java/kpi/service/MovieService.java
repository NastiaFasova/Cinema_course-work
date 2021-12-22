package kpi.service;

import kpi.models.Movie;
import java.util.List;

public interface MovieService {
    Movie add(Movie movie);

    Movie get(long id);

    Movie add(Movie movie, String id);

    List<Movie> getAll();

    Movie getByTitle(String title);

    boolean deleteById(Long id);

    Movie getByApi(String apiId);
}
