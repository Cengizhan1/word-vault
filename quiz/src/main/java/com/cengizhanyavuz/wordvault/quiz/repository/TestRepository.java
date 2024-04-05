package com.cengizhanyavuz.wordvault.quiz.repository;


import com.cengizhanyavuz.wordvault.quiz.model.Test;
import com.cengizhanyavuz.wordvault.quiz.model.TestState;
import com.cengizhanyavuz.wordvault.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Long> {
    Optional<Test> findByUserAndTestState(User user, TestState testState);
    Boolean existsByUserAndTestState(User user, TestState testState);

    List<Test> findAllOrderById(Long id);
}
