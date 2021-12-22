package kpi.models.dto.request;

import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
public class MovieSessionRequestDto {
    @NotNull(message = "MovieTitle can't be null")
    @Size(min = 1, max = 40)
    private String movieTitle;
    @NotNull(message = "CinemaHallId can't be null")
    private Long cinemaHallId;
    @NotNull(message = "ShowTime can't be null")
    private String showTime;
    @Positive
    @Min(0)
    private int maxTicketCount;
    @Positive
    @Min(0)
    private int currentTicketCount;
    @Positive
    private double price;

}
