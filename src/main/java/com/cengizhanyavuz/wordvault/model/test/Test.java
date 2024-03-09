package com.cengizhanyavuz.wordvault.model.test;


import com.cengizhanyavuz.wordvault.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime answeredDate;
    private TestState testState = TestState.CREATED;
    private int correctAnswers = 0;
    private int wrongAnswers = 0;

    @OneToMany(mappedBy = "relationTest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TestWords> relationTestWordList;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
