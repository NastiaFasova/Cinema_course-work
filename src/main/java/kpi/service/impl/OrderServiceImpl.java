package kpi.service.impl;

import kpi.exception.NotEnoughMoneyException;
import kpi.models.Bill;
import kpi.repository.OrderRepository;
import kpi.models.Order;
import kpi.models.Ticket;
import kpi.models.User;
import kpi.service.BillService;
import kpi.service.OrderService;
import kpi.service.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final BillService billService;

    private final ShoppingCartService shoppingCartService;

    public OrderServiceImpl(OrderRepository orderRepository, BillService billService, ShoppingCartService shoppingCartService) {
        this.orderRepository = orderRepository;
        this.billService = billService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        double overallPrice = tickets.stream()
                .map(Ticket::getPrice)
                .reduce(0D, Double::sum);
        if (user.getBill().getAmountOfMoney() >= overallPrice) {
            Order order = new Order();
            order.setOrderDate(LocalDateTime.now());
            order.setTickets(tickets);
            order.setUser(user);
            shoppingCartService.clear(shoppingCartService.getByUser(user));
            orderRepository.save(order);
            Bill bill = user.getBill();
            bill.setAmountOfMoney(bill.getAmountOfMoney() - overallPrice);
            billService.save(bill);
            return order;
        }
        throw new NotEnoughMoneyException("Not enough money");
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderRepository.getOrderHistory(user);
    }
}
