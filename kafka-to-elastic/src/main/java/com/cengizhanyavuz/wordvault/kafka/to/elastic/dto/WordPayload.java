package com.cengizhanyavuz.wordvault.kafka.to.elastic.dto;

import lombok.Data;

@Data
public class WordPayload {

    private String op;
    private WordDto before;
    private WordDto after;
}