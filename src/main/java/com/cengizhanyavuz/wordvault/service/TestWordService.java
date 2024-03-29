package com.cengizhanyavuz.wordvault.service;

import com.cengizhanyavuz.wordvault.model.Word;
import com.cengizhanyavuz.wordvault.model.test.Test;
import com.cengizhanyavuz.wordvault.model.test.TestWord;
import com.cengizhanyavuz.wordvault.repository.TestWordRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.cengizhanyavuz.wordvault.constants.PointConstants.*;
@Service
public class TestWordService {

    private final TestWordRepository testWordRepository;
    private final WordService wordService;

    public TestWordService(TestWordRepository testWordRepository, WordService wordService) {
        this.testWordRepository = testWordRepository;
        this.wordService = wordService;
    }

    protected List<TestWord> getWords(Test test) {
        List<Word> words = wordService.getWords();
        return testWordRepository.saveAll(words.stream().map(word ->
                new TestWord(
                        null,
                        word.getTr(),
                        word.getEn(),
                        false,
                        test,
                        word)
        ).toList());
    }



    protected List<TestWord> getWordsByTestId(Long testId) {
        return testWordRepository.findAllByTestId(testId);
    }

    @Async
    protected void updateWordsElo(List<TestWord> testWords) {
            List<Word> words = new ArrayList<>();
            for (TestWord testWord : testWords) {
                Word word = testWord.getWord();
                updateEloOfWord(word,
                        testWord.isCorrect() ? WORD_POINTS_TO_INCREASED : WORD_POINTS_TO_DECREASED);
                words.add(word);
            }
            wordService.saveAll(words);
    }

    @Async
    private void updateEloOfWord(Word word, int point) {
        word.setElo(word.getElo() + point);
    }
}
