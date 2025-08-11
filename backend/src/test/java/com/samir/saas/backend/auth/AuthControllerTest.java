package com.samir.saas.backend.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samir.saas.backend.auth.dto.LoginRequest;
import com.samir.saas.backend.auth.dto.RegisterRequest;
import com.samir.saas.backend.infra.config.SecurityConfig;
import com.samir.saas.backend.user.domain.UserRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AuthController.class)
@Import(SecurityConfig.class) 
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthService authService;
    
    @MockitoBean
    private JwtService jwtService;
    
    @MockitoBean
    private UserRepository userRepository;
    @Test
    void register_shouldReturn201_whenValidRequest() throws Exception {
        RegisterRequest req = new RegisterRequest();
        req.email = "user@example.com";
        req.password = "password123";
        req.fullName = "John Doe";

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
    }

    @Test
    void register_shouldReturn400_whenInvalidEmail() throws Exception {
        RegisterRequest req = new RegisterRequest();
        req.email = "invalid";
        req.password = "password123";
        req.fullName = "John Doe";

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void login_shouldReturnToken_whenValidRequest() throws Exception {
        LoginRequest req = new LoginRequest();
        req.email = "user@example.com";
        req.password = "password123";

        Mockito.when(authService.login(anyString(), anyString())).thenReturn("mock-jwt-token");

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mock-jwt-token"));
    }

    @Test
    void should_return404_whenInvalidRoute() throws Exception {
        mockMvc.perform(post("/auth/invalid"))
                .andExpect(status().isNotFound());
    }
}