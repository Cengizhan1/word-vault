package com.cengizhanyavuz.wordvault.repository;

import com.cengizhanyavuz.wordvault.model.test.TestWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestWordRepository extends JpaRepository<TestWord, Long> {
}
