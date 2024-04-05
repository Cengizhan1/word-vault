package com.cengizhanyavuz.wordvault.controller;

import com.cengizhanyavuz.wordvault.dto.test.TestResultDto;
import com.cengizhanyavuz.wordvault.dto.test.TestStartDto;
import com.cengizhanyavuz.wordvault.dto.test.TestFinishRequestDto;
import com.cengizhanyavuz.wordvault.service.TestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/finish")
    public ResponseEntity<TestResultDto> finishTest(@Valid @RequestBody TestFinishRequestDto testFinishRequestDto) {
        return ResponseEntity.ok(service.finishTest(testFinishRequestDto));
    }
}
