package com.cengizhanyavuz.wordvault.controller;

import com.cengizhanyavuz.wordvault.dto.UserDto;
import com.cengizhanyavuz.wordvault.dto.request.LoginRequest;
import com.cengizhanyavuz.wordvault.dto.request.RegisterRequest;
import com.cengizhanyavuz.wordvault.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> addUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.createUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> generateToken(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }
}