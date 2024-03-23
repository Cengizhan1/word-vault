package com.cengizhanyavuz.wordvault.controller;

import com.cengizhanyavuz.wordvault.dto.WordDto;
import com.cengizhanyavuz.wordvault.dto.request.WordCreateRequest;
import com.cengizhanyavuz.wordvault.dto.request.WordUpdateRequest;
import com.cengizhanyavuz.wordvault.service.WordService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/api/word")
public class WordController {
    private final WordService service;

    public WordController(WordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WordDto> createWord(@RequestBody WordCreateRequest request){
        return ResponseEntity.ok(service.createWord(request));
    }

    @GetMapping
    public ResponseEntity<List<WordDto>> getAllWord(){
        return ResponseEntity.ok(service.getAllWords());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WordDto> getWordById(@PathVariable Long id){
        return ResponseEntity.ok(service.findWordById(id));
    }
}
