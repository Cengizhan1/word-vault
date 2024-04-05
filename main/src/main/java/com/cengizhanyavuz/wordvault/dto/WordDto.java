package com.cengizhanyavuz.wordvault.dto;

import com.cengizhanyavuz.wordvault.model.Word;

public record WordDto(
        Long id,
        String tr,
        String en,
        String it,
        String alm,
        Integer elo,
        Boolean isApproved
) {
    public static WordDto convert(Word word) {
        return new WordDto(
                word.getId(),
                word.getTr(),
                word.getEn(),
                word.getIt(),
                word.getAlm(),
                word.getElo(),
                word.getIsApproved()
        );
    }
}
