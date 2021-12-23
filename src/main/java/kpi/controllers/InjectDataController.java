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

    private final MovieSessionController movieSessionController;

    public InjectDataController(RoleService roleService, UserService userService,
                                BillService billService, ShoppingCartService shoppingCartService,
                                MovieService movieService, CinemaHallService cinemaHallService,
                                MovieSessionController movieSessionController) {
        this.roleService = roleService;
        this.userService = userService;
        this.billService = billService;
        this.shoppingCartService = shoppingCartService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.movieSessionController = movieSessionController;
    }

    @PostConstruct
    public void injectRoles() {
        Role userRole = new Role();
        Bill bill = new Bill(0D);
        userRole.setRoleName(Role.RoleName.USER);
        User user = new User();
        user.setEmail("user@ukr.net");
        user.setPassword("111111");
        user.setFirstname("Vitalii");
        user.setLastname("Tsal");
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
        Movie movie5 = new Movie("tt8110232", "http://www.omdbapi.com/?apikey=***&i=tt8110232",
                "The Many Saints of Newark");
        Movie movie6 = new Movie("tt1074638", "http://www.omdbapi.com/?apikey=***&i=tt1074638",
                "Skyfall");
        Movie movie7 = new Movie("tt0110912", "http://www.omdbapi.com/?apikey=***&i=tt0110912",
                "Pulp Fiction");
        Movie movie8 = new Movie("tt0449467", "http://www.omdbapi.com/?apikey=***&i=tt0449467",
                "Babel");
        Movie movie9 = new Movie("tt0103776", "http://www.omdbapi.com/?apikey=***&i=tt0103776",
                "Batman Returns");
        Movie movie10 = new Movie("tt10872600", "http://www.omdbapi.com/?apikey=***&i=tt10872600",
                "Spider-Man: No Way Home");
        Movie movie11 = new Movie("tt0114369", "http://www.omdbapi.com/?apikey=***&i=tt0114369",
                "Se7en");
        Movie movie12 = new Movie("tt4244994", "http://www.omdbapi.com/?apikey=***&i=tt4244994",
                "The Last Duel");
        Movie movie13 = new Movie("tt0870154", "http://www.omdbapi.com/?apikey=***&i=tt0870154",
                "Jungle Cruise");
        Movie movie14 = new Movie("tt5463162", "http://www.omdbapi.com/?apikey=***&i=tt5463162",
                "Deadpool 2");
        Movie movie15 = new Movie("tt10919420", "http://www.omdbapi.com/?apikey=***&i=tt10919420",
                "Ojing-eo geim");
        Movie movie16 = new Movie("tt4574334", "http://www.omdbapi.com/?apikey=***&i=tt4574334",
                "Stranger Things");
        Movie movie17 = new Movie("tt6468322", "http://www.omdbapi.com/?apikey=***&i=tt6468322",
                "La casa de papel");
        Movie movie18 = new Movie("tt10795658", "http://www.omdbapi.com/?apikey=***&i=tt10795658",
                "Alice in Worderland");
        movieService.add(movie1);
        movieService.add(movie2);
        movieService.add(movie3);
        movieService.add(movie4);
        movieService.add(movie5);
        movieService.add(movie6);
        movieService.add(movie7);
        movieService.add(movie8);
        movieService.add(movie9);
        movieService.add(movie10);
        movieService.add(movie11);
        movieService.add(movie12);
        movieService.add(movie13);
        movieService.add(movie14);
        movieService.add(movie15);
        movieService.add(movie16);
        movieService.add(movie17);
        movieService.add(movie18);
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
        CinemaHall cinemaHall4 = CinemaHall.builder().description("In descriptive writing, the author does not just " +
                "tell the reader what was seen, felt, tested, smelled, or heard.")
                .capacity(66)
                .title("Super Hall").build();
        CinemaHall cinemaHall5 = CinemaHall.builder().description("Halls – another versatile and cheap venue hire " +
                "option. And the best thing? There are plenty of cheap halls to choose from in every district of London.")
                .capacity(80)
                .title("Cheapest hall").build();
        cinemaHallService.add(cinemaHall1);
        cinemaHallService.add(cinemaHall2);
        cinemaHallService.add(cinemaHall3);
        cinemaHallService.add(cinemaHall4);
        cinemaHallService.add(cinemaHall5);
        MovieSessionRequestDto movieSession
                = new MovieSessionRequestDto("Веном", 1L
                , "2021-12-22T16:16:54.482",
                150, 0, 150);
        movieSessionController.add(movieSession);
    }
}