package com.cengizhanyavuz.wordvault.dto.request.kafka;

import lombok.Data;

@Data
public class WordListener {

    private WordPayload payload;
}