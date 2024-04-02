package com.cengizhanyavuz.wordvault.repository;


import com.cengizhanyavuz.wordvault.model.test.Test;
import com.cengizhanyavuz.wordvault.model.test.TestState;
import com.cengizhanyavuz.wordvault.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Long> {
    Optional<Test> findByUserAndTestState(User user, TestState testState);
    Boolean existsByUserAndTestState(User user, TestState testState);

    List<Test> findAllOrderById(Long id);
}
