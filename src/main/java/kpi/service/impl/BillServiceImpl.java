package kpi.service.impl;
import kpi.exception.NotFoundByIdException;
import kpi.models.Bill;
import kpi.models.User;
import kpi.repository.BillRepository;
import kpi.repository.UserRepository;
import kpi.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final UserRepository userRepository;

    @Autowired
    public BillServiceImpl(BillRepository billRepository, UserRepository userRepository) {
        this.billRepository = billRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Bill save(Bill bill) {
        User user = userRepository.findById(bill.getUser().getId())
                .orElseThrow(() -> new NotFoundByIdException("The user can't be found by id"));
        user.setBill(bill);
        bill.setUser(user);
        userRepository.save(user);
        bill.setId(user.getId());
        billRepository.save(bill);
        return bill;
    }

    @Override
    public Bill save(Bill bill, Long id) {
        Bill current = findById(id);
        current.setUser(bill.getUser());
        current.setAmountOfMoney(bill.getAmountOfMoney());
        return billRepository.save(current);
    }

    @Override
    public boolean delete(Long billId) {
        billRepository.deleteById(billId);
        return true;
    }

    @Override
    public Bill findById(Long billId) {
        return billRepository.findById(billId)
                .orElseThrow(() -> new NotFoundByIdException("The bill can't be found by id"));
    }

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Bill registerNewBill(User user) {
        Bill bill = new Bill();
        bill.setUser(user);
        return billRepository.save(bill);
    }

}
