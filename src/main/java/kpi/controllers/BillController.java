package kpi.controllers;

import kpi.models.Bill;
import kpi.models.User;
import kpi.models.dto.BillDto;
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
    public Bill save(@RequestBody @Validated BillDto bill) {
        return billService.save(billMapper.getBill(bill));
    }

    @PatchMapping("/bill")
    public Bill update(@RequestBody @Validated BillDto bill, Authentication authentication) {
        Long billId = userService.getByEmail(authentication.getName()).getBill().getId();
        return billService.save(billMapper.getBill(bill), billId);
    }

    @GetMapping("/bill")
    public BillDto viewBill(Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());
        return billMapper.getBillDto(user.getBill());
    }
}
