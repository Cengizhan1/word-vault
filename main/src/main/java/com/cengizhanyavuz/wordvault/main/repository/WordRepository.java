package com.cengizhanyavuz.wordvault.main.repository;

import com.cengizhanyavuz.wordvault.main.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WordRepository extends JpaRepository<Word, Long> {

//    @Transactional
//    @Modifying
//    @Query("UPDATE Word w SET w.elo = w.elo + :points WHERE w.lastAnsweredDate < :date")
//    void increaseElo(int points,LocalDateTime date);

    @Query(value = "SELECT * FROM words " +
            "WHERE is_approved = true " +
            "ORDER BY RAND() * " +
            "(CASE " +
            "    WHEN elo = :userElo THEN 1 " +
            "    ELSE -LOG(RAND()) / ABS(:userElo - elo) " +
            "END) DESC " +
            "LIMIT :count", nativeQuery = true)
    List<Word> findRandomWords(@Param("count") int count, @Param("userElo") int userElo);


    Boolean existsByTr(String tr);

    List<Word> findAllByIsApprovedTrue();

    Optional<Word> findRandomWordByIsApprovedFalse();
}
