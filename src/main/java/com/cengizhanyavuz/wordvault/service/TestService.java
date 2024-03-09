package com.cengizhanyavuz.wordvault.service;

import com.cengizhanyavuz.wordvault.dto.TestStartDto;
import com.cengizhanyavuz.wordvault.model.Word;
import com.cengizhanyavuz.wordvault.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    private final TestRepository testRepository;
    private final WordService wordService;


    public TestService(TestRepository testRepository, WordService wordService) {
        this.testRepository = testRepository;
        this.wordService = wordService;
    }

    public TestStartDto startTest() {
       List<Word> words = wordService.getWordByUserElo();
       return new TestStartDto(words.stream().map(Word::getTr).toList());
    }


}
