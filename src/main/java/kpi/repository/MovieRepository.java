package kpi.repository;

import kpi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("select m from Movie m where m.title = :title")
    Movie findByTitle(String title);

    Movie findByApiId(String apiId);
}

