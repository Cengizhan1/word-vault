package com.cengizhanyavuz.wordvault.main.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import static com.cengizhanyavuz.wordvault.config.PointConstants.WORD_POINT;

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
    private Integer elo = WORD_POINT;
    private Boolean isApproved = true; // true for testing
}
