package kpi.models.mapper;

import kpi.models.ShoppingCart;
import kpi.models.dto.response.ShoppingCartResponseDto;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class ShoppingCartMapper {
    private final TicketMapper ticketMapper;

    public ShoppingCartMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public ShoppingCartResponseDto getShoppingCartResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(shoppingCart.getId());
        shoppingCartResponseDto.setTickets(shoppingCart.getTickets()
                .stream()
                .map(ticketMapper::getTicketResponseDto)
                .collect(Collectors.toList()));
        return shoppingCartResponseDto;
    }
}
