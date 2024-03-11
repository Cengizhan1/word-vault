package com.cengizhanyavuz.wordvault.repository;

import com.cengizhanyavuz.wordvault.model.GlobalWord;
import com.cengizhanyavuz.wordvault.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GlobalWordRepository extends JpaRepository<GlobalWord, Long> {

    @Query(value = "SELECT * FROM global_words ORDER BY RAND() LIMIT :count", nativeQuery = true)
    List<GlobalWord> findRandomWords(@Param("count") int count);
}
