package com.cengizhanyavuz.wordvault.quiz.service;

import com.cengizhanyavuz.wordvault.main.model.Word;
import com.cengizhanyavuz.wordvault.main.service.WordService;
import com.cengizhanyavuz.wordvault.quiz.model.Test;
import com.cengizhanyavuz.wordvault.quiz.model.TestWord;
import com.cengizhanyavuz.wordvault.quiz.repository.TestWordRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import static com.cengizhanyavuz.wordvault.config.PointConstants.*;


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
    protected void updateEloOfWord(Word word, int point) {
        word.setElo(word.getElo() + point);
    }
}
