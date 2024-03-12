package com.cengizhanyavuz.wordvault.service;

import com.cengizhanyavuz.wordvault.dto.test.TestResultDto;
import com.cengizhanyavuz.wordvault.dto.test.TestStartDto;
import com.cengizhanyavuz.wordvault.dto.test.TestFinishRequestDto;
import com.cengizhanyavuz.wordvault.exception.ActiveTestAlreadyExistsException;
import com.cengizhanyavuz.wordvault.exception.TestNotFoundException;
import com.cengizhanyavuz.wordvault.model.test.Test;
import com.cengizhanyavuz.wordvault.model.test.TestState;
import com.cengizhanyavuz.wordvault.model.test.TestWord;
import com.cengizhanyavuz.wordvault.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class TestService {

    private final TestRepository testRepository;
    private final TestWordService testWordService;
    private final UserService userService;


    public TestService(TestRepository testRepository, TestWordService testWordService, UserService userService) {
        this.testRepository = testRepository;
        this.testWordService = testWordService;
        this.userService = userService;
    }

    public TestStartDto startPersonalTest() {
        checkCurrentTest();
        Test test = new Test();
        test.setUser(userService.getCurrentUser());
        testRepository.save(test);
        List<TestWord> words = testWordService.getWords(test);
        return new TestStartDto(words.stream().map(TestWord::getQuestion).toList());
    }


    public TestResultDto finishPersonalTest(TestFinishRequestDto testFinishRequestDto) {
        return getTestResultDto(testFinishRequestDto);
    }

    public TestStartDto startGlobalTest() {
        checkCurrentTest();
        Test test = new Test();
        test.setUser(userService.getCurrentUser());
        testRepository.save(test);
        List<TestWord> words = testWordService.getGlobalWords(test);
        return new TestStartDto(words.stream().map(TestWord::getQuestion).toList());
    }


    public TestResultDto finishGlobalTest(TestFinishRequestDto testFinishRequestDto) {
        return getTestResultDto(testFinishRequestDto);
    }

    private TestResultDto getTestResultDto(TestFinishRequestDto testFinishRequestDto) {
        Test test =findCurrentTest();
        TestResultDto testResultDto = checkTestResult(testFinishRequestDto, test.getId());
        test.setTestState(TestState.ANSWERED);
        test.setCorrectAnswers(testResultDto.correctAnswers());
        test.setWrongAnswers(testResultDto.wrongAnswers());
        test.setAnsweredDate(LocalDateTime.now());
        testRepository.save(test);
        return testResultDto;
    }

    private TestResultDto checkTestResult(TestFinishRequestDto testFinishRequestDto,Long testId) {
        int correctAnswers = 0;
        int wrongAnswers = 0;
        List<TestWord> testWords = testWordService.getWordsByTestId(testId);
        for (int i = 0; i < testWords.size(); i++) {
            if (testFinishRequestDto.answers().get(i).equalsIgnoreCase(testWords.get(i).getAnswer())) {
                correctAnswers++;
                testWords.get(i).setCorrect(true);
            } else {
                wrongAnswers++;
            }
        }
        int finalCorrectAnswers = correctAnswers;
        int finalWrongAnswers = wrongAnswers;
        CompletableFuture.runAsync(() -> {
             testWordService.updateWordsElo(testWords);
             userService.updateUserElo(finalCorrectAnswers, finalWrongAnswers);
        });
        return new TestResultDto(finalCorrectAnswers, finalWrongAnswers);
    }

    protected Test findCurrentTest() {
        return testRepository.findByUserAndTestState(userService.getCurrentUser(), TestState.CREATED)
                .orElseThrow(
                () -> new TestNotFoundException("Test not found")
        );
    }

    protected void checkCurrentTest() {
        if (testRepository.existsByUserAndTestState(userService.getCurrentUser(), TestState.CREATED)) {
            throw new ActiveTestAlreadyExistsException("Active test already exists");
        }
    }
}
