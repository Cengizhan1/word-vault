package com.cengizhanyavuz.wordvault.main.repository;

import com.cengizhanyavuz.wordvault.main.model.test.TestWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestWordRepository extends JpaRepository<TestWord, Long> {
    List<TestWord> findAllByTestId(Long testId);
}
