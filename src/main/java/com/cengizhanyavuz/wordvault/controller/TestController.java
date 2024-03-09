package com.cengizhanyavuz.wordvault.controller;

import com.cengizhanyavuz.wordvault.dto.TestStartDto;
import com.cengizhanyavuz.wordvault.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/test")
public class TestController {

    private final TestService service;

    public TestController(TestService service) {
        this.service = service;
    }

    @GetMapping("/start")
    public ResponseEntity<TestStartDto> startTest() {
        return ResponseEntity.ok(service.startTest());
    }
}
