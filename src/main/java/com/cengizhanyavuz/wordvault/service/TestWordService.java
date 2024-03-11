package com.cengizhanyavuz.wordvault.service;

import com.cengizhanyavuz.wordvault.model.GlobalWord;
import com.cengizhanyavuz.wordvault.model.Word;
import com.cengizhanyavuz.wordvault.model.test.Test;
import com.cengizhanyavuz.wordvault.model.test.TestWord;
import com.cengizhanyavuz.wordvault.repository.TestWordRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.cengizhanyavuz.wordvault.constants.PointConstants.*;

@Service
public class TestWordService {

    private final TestWordRepository testWordRepository;
    private final WordService wordService;
    private final GlobalWordService globalWordService;

    public TestWordService(TestWordRepository testWordRepository, WordService wordService, GlobalWordService globalWordService) {
        this.testWordRepository = testWordRepository;
        this.wordService = wordService;
        this.globalWordService = globalWordService;
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
                        word,
                        null)
        ).toList());
    }

    protected List<TestWord> getGlobalWords(Test test) {
        List<GlobalWord> globalWords = globalWordService.getGlobalWords();
        return testWordRepository.saveAll(globalWords.stream().map(word ->
                new TestWord(
                        null,
                        word.getTr(),
                        word.getEn(),
                        false,
                        test,
                        null,
                        word)
        ).toList());
    }

    protected List<TestWord> getWordsByTestId(Long testId) {
        return testWordRepository.findAllByTestId(testId);
    }

    @Async
    protected void updateWordsElo(List<TestWord> testWords) {
        if (testWords.get(0).getWord() != null){
            List<Word> words = new ArrayList<>();
            for (TestWord testWord : testWords) {
                Word word = testWord.getWord();
                updateProficiencyLevelOfWord(word,
                        decideDecreaseOrIncrease(testWord.isCorrect()));
                words.add(word);
            }
            wordService.saveAll(words);
        }else {
            List<GlobalWord> words = new ArrayList<>();
            for (TestWord testWord : testWords) {
                GlobalWord word = testWord.getGlobalWord();
                updateProficiencyLevelOfGlobalWord(word,
                        decideDecreaseOrIncrease(testWord.isCorrect()));
                words.add(word);
            }
            globalWordService.saveAll(words);
        }
    }

    @Async
    private void updateProficiencyLevelOfWord(Word word, int point) {
        word.setProficiencyLevel(word.getProficiencyLevel() + point);
    }
    @Async
    private void updateProficiencyLevelOfGlobalWord(GlobalWord word, int point) {
        word.setProficiencyLevel(word.getProficiencyLevel() + point);
    }

    @Async
    private int decideDecreaseOrIncrease(Boolean result) {
        return result ? WORD_POINTS_TO_DECREASED : WORD_POINTS_TO_INCREASED;
    }
}
