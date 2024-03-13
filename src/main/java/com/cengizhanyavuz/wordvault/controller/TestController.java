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

    @GetMapping("/personal/start")
    public ResponseEntity<TestStartDto> startPersonalTest() {
        return ResponseEntity.ok(service.startPersonalTest());
    }

    @PostMapping("/personal/finish")
    public ResponseEntity<TestResultDto> finishPersonalTest(@Valid @RequestBody TestFinishRequestDto testFinishRequestDto) {
        return ResponseEntity.ok(service.finishPersonalTest(testFinishRequestDto));
    }

    @GetMapping("/global/start")
    public ResponseEntity<TestStartDto> startGlobalTest() {
        return ResponseEntity.ok(service.startGlobalTest());
    }

    @PostMapping("/global/finish")
    public ResponseEntity<TestResultDto> finishGlobalTest(@RequestBody TestFinishRequestDto testFinishRequestDto) {
        return ResponseEntity.ok(service.finishGlobalTest(testFinishRequestDto));
    }
}
