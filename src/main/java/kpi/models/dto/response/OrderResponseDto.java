package kpi.models.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class OrderResponseDto {
    private Long id;
    private List<TicketResponseDto> tickets = new ArrayList<>();
    private String orderDate;
    private Long userId;

}
