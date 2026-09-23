package com.example.demo.repository.user;

import com.example.demo.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();

    User save(User user);

    Optional<User> findById(Long id);

    void delete(User user);

    Optional<User> findByName(String name);

    List<User> findAllWithDeals();
    Optional<User> findByEmail(String email);

}
