package kpi.security;

import kpi.exception.AuthenticationException;
import kpi.models.Role;
import kpi.models.User;
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

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     RoleService roleService,
                                     PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
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
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setBlocked(user.isBlocked());
        Role role = roleService.getRoleByName("USER");
        newUser.setRoles(Set.of(role));
        userService.add(newUser);
        shoppingCartService.registerNewShoppingCart(newUser);
        return newUser;
    }
}
