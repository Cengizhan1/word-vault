package com.cengizhanyavuz.wordvault.model;


import com.cengizhanyavuz.wordvault.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Data
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "words")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(nullable = false)
    private String tr;
    @Column(nullable = false)
    private String en;
    private String it;
    private String alm;
    private Integer elo = 100;
    private LocalDateTime lastAnsweredDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
