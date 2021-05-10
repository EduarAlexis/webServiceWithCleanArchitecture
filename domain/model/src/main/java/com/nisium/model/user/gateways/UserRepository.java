package com.nisium.model.user.gateways;

import java.util.List;

import com.nisium.model.user.User;

public interface UserRepository {
    List<User> getAll();
    User save(User user);
    User findByEmail(String email);
}
