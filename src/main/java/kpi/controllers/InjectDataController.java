package kpi.controllers;

import kpi.models.Bill;
import kpi.models.Role;
import kpi.models.User;
import kpi.service.BillService;
import kpi.service.RoleService;
import kpi.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

@Controller
public class InjectDataController {
    private final RoleService roleService;

    private final UserService userService;

    private final BillService billService;

    public InjectDataController(RoleService roleService, UserService userService, BillService billService) {
        this.roleService = roleService;
        this.userService = userService;
        this.billService = billService;
    }

    @PostConstruct
    public void injectRoles() {
        Role userRole = new Role();
        Bill bill = new Bill(125_000D);
        userRole.setRoleName(Role.RoleName.USER);
        User user = new User();
        user.setEmail("user@ukr.net");
        user.setPassword("111111");
        user.setFirstname("Oleksyy");
        user.setLastname("Prylipko");
        user.setRoles(Set.of(userRole));
        roleService.add(userRole);
        userService.add(user);
        bill.setUser(user);
        billService.save(bill);

        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("999999");
        admin.setFirstname("Vladyslav");
        admin.setLastname("Kornilov");
        admin.setRoles(Set.of(adminRole));
        roleService.add(adminRole);
        userService.add(admin);
        Bill billAdmin = new Bill(admin.getId(), 12_000D);
        billAdmin.setUser(admin);
        billService.save(billAdmin);
    }
}
