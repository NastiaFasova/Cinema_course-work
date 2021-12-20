package kpi.service;

import kpi.models.User;

import java.util.List;

public interface UserService {
    User add(User user);

    List<User> getAll();

    User getByEmail(String email);

    User get(Long id);

    User block(Long id);

    User unblock(Long id);
}
