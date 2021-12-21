package kpi.models.dto;

import kpi.service.MovieService;
import kpi.validation.Unique;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MovieDto {
    @NotNull(message = "MovieId can't be null")
    private Long id;
    @NotNull(message = "MovieTitle can't be null")
    @Size(min = 1, max = 40)
    private String title;
    @Unique(service = MovieService.class, fieldName = "apiId", message = "This movie is already in database")
    private String apiId;
    private String link;
}
