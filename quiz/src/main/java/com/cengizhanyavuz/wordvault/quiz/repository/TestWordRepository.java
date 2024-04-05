package com.cengizhanyavuz.wordvault.quiz.repository;

import com.cengizhanyavuz.wordvault.quiz.model.TestWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestWordRepository extends JpaRepository<TestWord, Long> {
    List<TestWord> findAllByTestId(Long testId);
}
