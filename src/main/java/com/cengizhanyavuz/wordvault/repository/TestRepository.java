package com.cengizhanyavuz.wordvault.repository;


import com.cengizhanyavuz.wordvault.model.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
