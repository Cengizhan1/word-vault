package com.cengizhanyavuz.wordvault.repository;

import com.cengizhanyavuz.wordvault.model.Word;
import com.cengizhanyavuz.wordvault.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {

    List<Word> findAllByUser(User user);
}
