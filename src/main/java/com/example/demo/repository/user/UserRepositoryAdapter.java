package com.example.demo.repository.user;

import com.example.demo.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id);
    }

    @Override
    public void delete(User user) {
        userJpaRepository.delete(user);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userJpaRepository.findByName(name);
    }

    @Override
    public List<User> findAllWithDeals() {
        return userJpaRepository.findAllWithDeals();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }
}
