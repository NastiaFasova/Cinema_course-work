package kpi.controllers;

import kpi.models.Order;
import kpi.models.ShoppingCart;
import kpi.models.Ticket;
import kpi.models.User;
import kpi.models.dto.request.OrderRequestDto;
import kpi.models.dto.response.OrderResponseDto;
import kpi.models.mapper.OrderMapper;
import kpi.service.OrderService;
import kpi.service.ShoppingCartService;
import kpi.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, UserService userService,
                           ShoppingCartService shoppingCartService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public OrderResponseDto complete(Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        List<Ticket> tickets = new ArrayList<>(shoppingCart.getTickets());
        return orderMapper.getOrderResponseDto(orderService.completeOrder(tickets, user));
    }

    @GetMapping
    public List<OrderResponseDto> getAll(Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());
        List<Order> orders = orderService.getOrderHistory(user);
        return orders.stream()
                .map(orderMapper::getOrderResponseDto)
                .collect(Collectors.toList());
    }
}
