package kpi.models.dto.response;

import kpi.models.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ShoppingCartResponseDto {
    private Long id;
    private List<TicketResponseDto> tickets = new ArrayList<>();
}
