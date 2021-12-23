package kpi.controllers;

import kpi.models.User;
import kpi.models.dto.BillDto;
import kpi.models.dto.response.BillResponseDto;
import kpi.models.mapper.BillMapper;
import kpi.service.BillService;
import kpi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class BillController {
    private final BillService billService;
    private final BillMapper billMapper;
    private final UserService userService;

    @Autowired
    public BillController(BillService billService, BillMapper billMapper, UserService userService) {
        this.billService = billService;
        this.billMapper = billMapper;
        this.userService = userService;
    }

    @PostMapping("/bill")
    public BillResponseDto save(@RequestBody @Validated BillDto bill) {
        return billMapper.getBillResponseDto(billService.save(billMapper.getBill(bill)));
    }

    @PatchMapping("/bill")
    public BillResponseDto update(@RequestBody @Validated BillDto bill, Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());
        bill.setUserId(user.getId());
        Long billId = user.getBill().getId();
        return billMapper.getBillResponseDto(billService.save(billMapper.getBill(bill), billId));
    }

    @GetMapping("/bill")
    public BillResponseDto viewBill(Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());
        return billMapper.getBillResponseDto(user.getBill());
    }
}
