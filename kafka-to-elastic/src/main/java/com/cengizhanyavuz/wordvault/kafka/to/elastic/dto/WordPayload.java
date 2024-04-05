package com.cengizhanyavuz.wordvault.kafka.to.elastic.dto;

import com.cengizhanyavuz.wordvault.kafka.to.elastic.model.Word;
import lombok.Data;

@Data
public class WordPayload {

    private String op;
    private Word before;
    private Word after;
}