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

    List<Word> findAllByUser(User user);


    @Transactional
    @Modifying
    @Query("UPDATE Word w SET w.proficiencyLevel = w.proficiencyLevel + :points WHERE w.lastAnsweredDate < :date")
    void increaseProficiencyLevels(int points,LocalDateTime date);


    @Query(value = "SELECT * FROM words ORDER BY RAND() LIMIT :count", nativeQuery = true)
    List<Word> findRandomWords(@Param("count") int count);

    Boolean existsByUserAndTr(User user, String tr);
}
