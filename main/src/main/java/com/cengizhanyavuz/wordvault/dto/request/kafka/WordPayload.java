package com.cengizhanyavuz.wordvault.dto.request.kafka;

import com.cengizhanyavuz.wordvault.dto.WordDto;
import lombok.Data;

@Data
public class WordPayload {

    private String op;
    private WordDto before;
    private WordDto after;
}