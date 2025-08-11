package com.samir.saas.backend.auth;

import com.samir.saas.backend.auth.dto.RegisterRequest;
import com.samir.saas.backend.auth.dto.LoginRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest req) {
        authService.register(req.email, req.password, req.fullName);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest req) {
        String token = authService.login(req.email, req.password);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
