package com.cengizhanyavuz.wordvault.main.controller;

import com.cengizhanyavuz.wordvault.main.dto.request.LoginRequest;
import com.cengizhanyavuz.wordvault.main.dto.request.RegisterRequest;
import com.cengizhanyavuz.wordvault.main.dto.response.AuthenticationResponse;
import com.cengizhanyavuz.wordvault.main.service.auth.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    private final AuthenticationService service;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (Exception e) {
            logger.error("register method threw an exception", e);
            throw e;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody LoginRequest request
    ) {
        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (Exception e) {
            logger.error("authenticate method threw an exception", e);
            throw e;
        }
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}
