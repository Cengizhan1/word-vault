package com.cengizhanyavuz.wordvault.repository;

import com.cengizhanyavuz.wordvault.model.Word;
import com.cengizhanyavuz.wordvault.model.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static com.cengizhanyavuz.wordvault.constants.PointConstants.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WordRepository extends JpaRepository<Word, Long> {

//    @Transactional
//    @Modifying
//    @Query("UPDATE Word w SET w.elo = w.elo + :points WHERE w.lastAnsweredDate < :date")
//    void increaseElo(int points,LocalDateTime date);

    @Query(value = "SELECT * FROM words WHERE is_approved = true AND elo > 0 AND (last_answered_date < :date OR last_answered_date IS NULL) ORDER BY RAND() LIMIT :count", nativeQuery = true)
    List<Word> findRandomWords(@Param("count") int count , @Param("date") LocalDateTime date);

    Boolean existsByTr(String tr);

    List<Word> findAllByIsApprovedTrue();
}
