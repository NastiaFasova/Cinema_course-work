package kpi.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartRequestDto {
    @NotNull(message = "Enter the id of movieSession")
    private Long movieSessionId;
}
