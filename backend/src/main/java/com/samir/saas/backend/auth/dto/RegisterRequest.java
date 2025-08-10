package com.samir.saas.backend.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @Email(message="Invalid email format")
    @NotBlank(message="Email is required")
    public String email;

    @Size(min=8, message="Password must be at least 8 characters long")
    @NotBlank(message="Password is required")
    public String password;

    @NotBlank(message="Full name is required")
    public String fullName;

    public RegisterRequest() {}
}
