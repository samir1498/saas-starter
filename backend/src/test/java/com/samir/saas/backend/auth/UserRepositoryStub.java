package com.samir.saas.backend.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.samir.saas.backend.user.domain.User;
import com.samir.saas.backend.user.domain.UserRepository;

public class UserRepositoryStub implements UserRepository {

    private final Map<UUID, User> userStore = new HashMap<>();

    @Override
    public User save(User user) {
        userStore.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(userStore.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userStore.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public void deleteById(UUID id) {
        userStore.remove(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userStore.values().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }
}
