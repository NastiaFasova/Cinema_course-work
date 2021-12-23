package kpi.controllers;

import kpi.models.MovieSession;
import kpi.models.ShoppingCart;
import kpi.models.User;
import kpi.models.dto.request.ShoppingCartRequestDto;
import kpi.models.dto.request.TicketRequestDto;
import kpi.models.dto.response.ShoppingCartResponseDto;
import kpi.models.mapper.ShoppingCartMapper;
import kpi.service.MovieSessionService;
import kpi.service.ShoppingCartService;
import kpi.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final MovieSessionService movieSessionService;
    private final ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieSessionService movieSessionService,
                                  ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @PostMapping("/add-movie-session")
    public ShoppingCartResponseDto addMovieSession(Authentication authentication,
                                                   @RequestBody @Valid ShoppingCartRequestDto shoppingCartRequestDto) {
        User user = userService.getByEmail(authentication.getName());
        MovieSession movieSession = movieSessionService.get(shoppingCartRequestDto.getMovieSessionId());
        return shoppingCartMapper.getShoppingCartResponseDto(shoppingCartService
                .addSession(movieSession, user));
    }

    @DeleteMapping("/remove-movie-session/{ticket-id}")
    public ShoppingCartResponseDto removeMovieSession(Authentication authentication,
                                                   @RequestBody @Valid ShoppingCartRequestDto shoppingCartRequestDto,
                                                      @PathVariable("ticket-id") String ticketId) {
        User user = userService.getByEmail(authentication.getName());
        MovieSession movieSession = movieSessionService.get(shoppingCartRequestDto.getMovieSessionId());
        return shoppingCartMapper.getShoppingCartResponseDto(shoppingCartService
                .removeMovieSession(movieSession, user, Long.parseLong(ticketId)));
    }


    @GetMapping("/by-user")
    public ShoppingCartResponseDto getShoppingCartBuUserId(Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        return shoppingCartMapper.getShoppingCartResponseDto(shoppingCart);
    }
}
