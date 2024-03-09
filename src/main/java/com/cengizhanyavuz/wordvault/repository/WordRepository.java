package com.cengizhanyavuz.wordvault.repository;

import com.cengizhanyavuz.wordvault.model.Word;
import com.cengizhanyavuz.wordvault.model.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import static com.cengizhanyavuz.wordvault.constants.PointConstants.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WordRepository extends JpaRepository<Word, Long> {

    List<Word> findAllByUser(User user);
    @Modifying
    @Transactional
    @Query("UPDATE Word w SET w.lastAnsweredDate = w.lastAnsweredDate + :WORD_POINTS_TO_INCREASED WHERE w.lastAnsweredDate <= :cutoffDate")
    void increasePointsForOldWords(LocalDateTime cutoffDate);

    List<Word> findRandomWords(int count);
}
