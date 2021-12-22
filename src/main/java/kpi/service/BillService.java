package kpi.service;

import kpi.models.Bill;
import kpi.models.User;

import java.util.List;

public interface BillService {
    Bill save(Bill bill);

    Bill save(Bill bill, Long id);

    boolean delete(Long billId);

    Bill findById(Long billId);

    List<Bill> findAll();

    Bill registerNewBill(User newUser);
}
