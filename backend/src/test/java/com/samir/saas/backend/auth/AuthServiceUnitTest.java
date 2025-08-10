package com.samir.saas.backend.auth;

import com.samir.saas.backend.user.domain.User;
import org.junit.jupiter.api.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceUnitTest {
    private UserRepositoryStub repo;
    private PasswordEncoder encoder;
    private JwtService jwt;
    private AuthService auth;

    @BeforeEach
    void setUp() {
        repo = new UserRepositoryStub();
        encoder = new BCryptPasswordEncoder();
        // test secret 32+ chars
        jwt = new JwtService("test-secret-which-is-long-enough-123456");
        auth = new AuthService(repo, encoder, jwt);
    }

    @Test
    void register_success_then_userPersisted() {
        auth.register("u1@example.com", "strongPass1", "User One");
        assertTrue(repo.existsByEmail("u1@example.com"));
    }

    @Test
    void register_duplicateEmail_throwsConflict() {
        User u = User.builder()
                .id(UUID.randomUUID())
                .email("dup@example.com")
                .password(encoder.encode("pw"))
                .build();
        repo.save(u);

        assertThrows(RuntimeException.class,
                () -> auth.register("dup@example.com", "another", "X"));
    }

    @Test
    void login_success_returnsToken() {
        User u = User.builder()
                .id(UUID.randomUUID())
                .email("log@example.com")
                .password(encoder.encode("mypw12345"))
                .build();
        repo.save(u);

        String token = auth.login("log@example.com", "mypw12345");
        assertNotNull(token);
    }

    @Test
    void login_wrongPassword_throwsUnauthorized() {
        User u = User.builder()
                .id(UUID.randomUUID())
                .email("log2@example.com")
                .password(encoder.encode("mypw12345"))
                .build();
        repo.save(u);

        assertThrows(RuntimeException.class,
                () -> auth.login("log2@example.com", "badpass"));
    }

    @Test
    void login_unknownEmail_throwsUnauthorized() {
        assertThrows(RuntimeException.class,
                () -> auth.login("noone@example.com", "pw"));
    }
}
