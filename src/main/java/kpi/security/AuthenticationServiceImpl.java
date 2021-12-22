package kpi.security;

import kpi.exception.AuthenticationException;
import kpi.models.Bill;
import kpi.models.Role;
import kpi.models.User;
import kpi.service.BillService;
import kpi.service.RoleService;
import kpi.service.ShoppingCartService;
import kpi.service.UserService;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    private final ShoppingCartService shoppingCartService;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    private final BillService billService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     RoleService roleService,
                                     PasswordEncoder passwordEncoder, BillService billService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.billService = billService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDb = userService.getByEmail(email);
        if (userFromDb != null && passwordEncoder.matches(password, userFromDb.getPassword())) {
            return userFromDb;
        }
        throw new AuthenticationException("Wrong login or password");
    }

    @Override
    public User register(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setBlocked(user.isBlocked());
        Role role = roleService.getRoleByName("USER");
        newUser.setRoles(Set.of(role));
        Bill bill = new Bill(0D);
        bill.setUser(newUser);
        newUser.setBill(bill);
        userService.add(newUser);
        shoppingCartService.registerNewShoppingCart(newUser);
        return newUser;
    }
}
