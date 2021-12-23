package kpi.models.dto.response;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Data
public class BillResponseDto {
    private Long id;
    @Positive
    @Min(0)
    private Double amountOfMoney;
    private String email;
}
