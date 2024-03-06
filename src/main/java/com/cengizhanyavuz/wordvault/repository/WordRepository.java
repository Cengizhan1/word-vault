package com.cengizhanyavuz.wordvault.repository;

import com.cengizhanyavuz.wordvault.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}
