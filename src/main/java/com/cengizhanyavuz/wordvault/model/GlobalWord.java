package com.cengizhanyavuz.wordvault.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import static com.cengizhanyavuz.wordvault.constants.PointConstants.WORD_POINT;

@Data
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "global_words")
public class GlobalWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String tr;
    private String en;
    private String it;
    private String alm;
    private Integer elo = WORD_POINT;
}
