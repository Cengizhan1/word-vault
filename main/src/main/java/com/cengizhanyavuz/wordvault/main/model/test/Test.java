package com.cengizhanyavuz.wordvault.main.model.test;


import com.cengizhanyavuz.wordvault.main.model.user.User;
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
    @Enumerated(EnumType.STRING)
    private TestState testState = TestState.CREATED;
    private int correctAnswers = 0;
    private int wrongAnswers = 0;

    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TestWord> relationTestWordList;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
