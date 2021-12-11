package kpi.models.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ShoppingCartResponseDto {
    private Long id;
    private List<Long> ticketsId = new ArrayList<>();
}
