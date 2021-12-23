package kpi.repository;

import kpi.models.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieSessionRepository extends JpaRepository<MovieSession, Long> {
    @Query("from MovieSession ms JOIN FETCH ms.movie m JOIN FETCH ms.cinemaHall c "
            + "where m.id = :movieId "
            + "and ms.showTime > :dateStart "
            + "and ms.showTime < :dateEnd")
    List<MovieSession> getAvailableSessions(Long movieId, LocalDateTime dateStart, LocalDateTime dateEnd);

    @Query("from MovieSession ms JOIN FETCH ms.movie JOIN FETCH ms.cinemaHall")
    List<MovieSession> findAll();

    @Query("from MovieSession ms JOIN FETCH ms.movie m JOIN FETCH ms.cinemaHall c where c.id = :cinemaHallId and " +
            " ms.showTime > :dateStart and ms.showTime < :dateEnd ")
    MovieSession findByCinemaHallAndShowTime(Long cinemaHallId, LocalDateTime dateStart,
                                                       LocalDateTime dateEnd);

    @Query("from MovieSession ms JOIN FETCH ms.movie m JOIN FETCH ms.cinemaHall c where c.id = :cinemaHallId and " +
            "ms.showTime > :dateStart and ms.showTime < :dateEnd and m.id = :movieId")
    MovieSession findByCinemaHallAndShowTimeAndMovieId(Long movieId, Long cinemaHallId, LocalDateTime dateStart,
                                                       LocalDateTime dateEnd);
}
