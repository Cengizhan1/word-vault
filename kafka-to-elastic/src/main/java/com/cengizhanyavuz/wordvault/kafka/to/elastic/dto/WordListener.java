package com.cengizhanyavuz.wordvault.kafka.to.elastic.dto;

import lombok.Data;

@Data
public class WordListener {

    private WordPayload payload;
}