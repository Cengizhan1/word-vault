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

import java.util.List;
import java.util.Optional;

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

    public TestStartDto startTest() {
        checkCurrentTest();
        List<TestWord> words = testWordService.getWords();
        Test test = new Test();
        test.setRelationTestWordList(words);
        testRepository.save(test);
        return new TestStartDto(words.stream().map(TestWord::getQuestion).toList());
    }


    public TestResultDto finishTest(TestFinishRequestDto testFinishRequestDto) {
        Test test =findCurrentTest();
        test.setTestState(TestState.ANSWERED);
        test.setCorrectAnswers(7);
        test.setWrongAnswers(3);
        test.setAnsweredDate(testFinishRequestDto.lastAnsweredDate());
        testRepository.save(test);
        return new TestResultDto(test.getCorrectAnswers(), test.getWrongAnswers());
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
