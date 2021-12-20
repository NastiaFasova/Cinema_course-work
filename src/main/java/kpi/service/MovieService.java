package kpi.service;

import kpi.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();

    Movie getByTitle(String title);

    boolean deleteById(Long id);

    Movie getByApiId(String apiId);

    Page<Movie> findAllPaginated(String keyword, int page, int size, String sortField, String sortDir);
}
