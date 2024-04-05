package com.cengizhanyavuz.wordvault.model;

import com.cengizhanyavuz.wordvault.helper.Indices;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Setting;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = Indices.WORD_INDEX)
@Setting(settingPath = "static/es-settings.json")
public class Word {
    private Long id;
    @Column(nullable = false)
    private String tr;
    @Column(nullable = false)
    private String en;
    private String it;
    private String alm;
    private Integer elo;
    private Boolean isApproved;
}
