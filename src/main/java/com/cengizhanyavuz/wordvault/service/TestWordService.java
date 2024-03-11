package com.cengizhanyavuz.wordvault.service;

import com.cengizhanyavuz.wordvault.model.Word;
import com.cengizhanyavuz.wordvault.model.test.TestWord;
import com.cengizhanyavuz.wordvault.repository.TestWordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestWordService {

    private final TestWordRepository testWordRepository;
    private final WordService wordService;

    public TestWordService(TestWordRepository testWordRepository, WordService wordService) {
        this.testWordRepository = testWordRepository;
        this.wordService = wordService;
    }

    protected List<TestWord> getWords() {
        List<Word> words = wordService.getWordByUserElo();
        return testWordRepository.saveAll(words.stream().map(word ->
                new TestWord(
                        null,
                        word.getTr(),
                        null,
                        false,
                        null,
                        word)
        ).toList());
    }
}
