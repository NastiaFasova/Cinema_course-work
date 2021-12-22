package kpi.security;

import kpi.exception.AuthenticationException;
import kpi.models.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(User user) throws AuthenticationException;
}
