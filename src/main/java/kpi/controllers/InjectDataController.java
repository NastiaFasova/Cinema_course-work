package kpi.controllers;

import kpi.models.*;
import kpi.models.dto.request.MovieSessionRequestDto;
import kpi.service.*;

import java.util.Set;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

@Controller
public class InjectDataController {
    private final RoleService roleService;

    private final UserService userService;

    private final BillService billService;

    private final ShoppingCartService shoppingCartService;

    private final MovieService movieService;

    private final CinemaHallService cinemaHallService;

    private final BillController billController;

    private final MovieSessionController movieSessionController;

    public InjectDataController(RoleService roleService, UserService userService,
                                BillService billService, ShoppingCartService shoppingCartService,
                                MovieService movieService, CinemaHallService cinemaHallService, BillController billController, MovieSessionController movieSessionController) {
        this.roleService = roleService;
        this.userService = userService;
        this.billService = billService;
        this.shoppingCartService = shoppingCartService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.billController = billController;
        this.movieSessionController = movieSessionController;
    }

    @PostConstruct
    public void injectRoles() {
        Role userRole = new Role();
        Bill bill = new Bill(125_000D);
        userRole.setRoleName(Role.RoleName.USER);
        User user = new User();
        user.setEmail("user@ukr.net");
        user.setPassword("111111");
        user.setFirstname("Oleksyy");
        user.setLastname("Prylipko");
        user.setAvatarUrl("https://cq-esports.com/storage/uploads/posts/1197054/1.jpg");
        user.setRoles(Set.of(userRole));
        roleService.add(userRole);
        shoppingCartService.registerNewShoppingCart(user);
        userService.add(user);
        bill.setUserId(1L);
        billService.save(bill);


        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("999999");
        admin.setFirstname("Vladyslav");
        admin.setLastname("Kornilov");
        admin.setRoles(Set.of(adminRole));
        roleService.add(adminRole);
        userService.add(admin);
        Bill billAdmin = new Bill(admin.getId(), 12_000D);
        billAdmin.setUserId(2L);
        billService.save(billAdmin);

        Movie movie1 = new Movie("tt0137523", "http://www.omdbapi.com/?apikey=***&i=tt0137523",
                "Бойцовский клуб");
        Movie movie2 = new Movie("tt1270797", "http://www.omdbapi.com/?apikey=***&i=tt1270797",
                "Веном");
        Movie movie3 = new Movie("tt6334354",
                "http://www.omdbapi.com/?apikey=***&i=tt6334354", "Отряд самоубийц: Миссия навылет");
        Movie movie4 = new Movie("tt1160419", "http://www.omdbapi.com/?apikey=***&i=tt1160419",
                "Дюна");
        movieService.add(movie1);
        movieService.add(movie2);
        movieService.add(movie3);
        movieService.add(movie4);
        CinemaHall cinemaHall1 = CinemaHall.builder().description("A simple, comfortable hall for families. " +
                "Choose this one, if you want to spent your time with familiy and be satisfied")
                .capacity(50)
                .title("Hall A Green").build();
        CinemaHall cinemaHall2 = CinemaHall.builder().description("The most comfortable hall in our theatre. It has a" +
                " large choice of movies and you can afford a lot of different abilities here")
                .capacity(150)
                .title("Hall SSS - Super Class").build();
        CinemaHall cinemaHall3 = CinemaHall.builder().description("A small hall for pleasant pastime. You can watch " +
                "movies there with your friends or whatever.")
                .capacity(50)
                .title("Small Hall C").build();
        cinemaHallService.add(cinemaHall1);
        cinemaHallService.add(cinemaHall2);
        cinemaHallService.add(cinemaHall3);
        MovieSessionRequestDto movieSession
                = new MovieSessionRequestDto("Веном", 1L
                ,"2021-12-22T16:16:54.482",
                150, 0, 150);
        movieSessionController.add(movieSession);
    }
}