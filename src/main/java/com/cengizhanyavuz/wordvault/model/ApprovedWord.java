package com.cengizhanyavuz.wordvault.model;


import com.cengizhanyavuz.wordvault.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "approved_words")
public class ApprovedWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String tr;
    private String en;
    private String it;
    private String alm;
    private Integer proficiencyLevel = 100;
}
