package kpi.models.mapper;

import kpi.models.Bill;
import kpi.models.dto.BillDto;
import kpi.models.dto.response.BillResponseDto;
import kpi.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class BillMapper {
    private final UserService userService;

    public BillMapper(UserService userService) {
        this.userService = userService;
    }

    public BillDto getBillDto(Bill bill) {
        BillDto billDto = new BillDto();
        billDto.setUserId(bill.getUserId());
        billDto.setAmountOfMoney(bill.getAmountOfMoney());
        billDto.setId(bill.getId());
        return billDto;
    }

    public Bill getBill(BillDto billDto) {
        Bill bill = new Bill();
        bill.setUserId(billDto.getUserId());
        bill.setId(billDto.getId());
        bill.setAmountOfMoney(billDto.getAmountOfMoney());
        return bill;
    }

    public BillResponseDto getBillResponseDto(Bill bill) {
        BillResponseDto billResponseDto = new BillResponseDto();
        billResponseDto.setEmail(userService.get(bill.getUserId()).getEmail());
        billResponseDto.setId(bill.getId());
        billResponseDto.setAmountOfMoney(bill.getAmountOfMoney());
        return billResponseDto;
    }
}
