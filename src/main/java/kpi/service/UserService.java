package kpi.service;

import kpi.models.User;

import java.util.List;

public interface UserService {
    User add(User user);

    User add(User user, String id);

    User getByEmail(String email);

    User get(Long id);

    User block(Long id);

    User unblock(Long id);

    List<User> findAll(String keyword);

    List<User> findAll();

    boolean deleteById(Long id);
}
