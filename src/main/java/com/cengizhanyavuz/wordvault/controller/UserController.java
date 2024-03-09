package com.cengizhanyavuz.wordvault.controller;

import com.cengizhanyavuz.wordvault.dto.UserDto;
import com.cengizhanyavuz.wordvault.dto.request.UserUpdateRequest;
import com.cengizhanyavuz.wordvault.model.user.User;
import com.cengizhanyavuz.wordvault.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<UserDto> show() {
        return ResponseEntity.ok(service.getUser());
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody UserUpdateRequest request) {
        return ResponseEntity.ok(service.updateUser(request));
    }
}
