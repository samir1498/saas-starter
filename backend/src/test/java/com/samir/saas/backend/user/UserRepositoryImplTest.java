package com.samir.saas.backend.user;

import com.samir.saas.backend.user.domain.User;
import com.samir.saas.backend.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.samir.saas.backend.TestcontainersConfiguration;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@Testcontainers
@SpringBootTest
@Import(TestcontainersConfiguration.class)
@TestPropertySource(properties = "jwt.secret=12345678901234567890123456789012")
class UserRepositoryImplTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndRetrieveUser() {
        var id = UUID.randomUUID();
        String email = "test@example.com";
        String password = "secure";
        Instant now = Instant.now();

        User user = User.builder()
                .id(id)
                .email(email)
                .password(password)
                .createdAt(now)
                .updatedAt(now)
                .build();

        userRepository.save(user);
        System.out.println("User saved: " + user);

        Optional<User> found = userRepository.findByEmail(email);

        assertTrue(found.isPresent());
        assertEquals(user.getId(), found.get().getId());
        assertEquals(email, found.get().getEmail());
        assertEquals(password, found.get().getPassword());
    }
}
