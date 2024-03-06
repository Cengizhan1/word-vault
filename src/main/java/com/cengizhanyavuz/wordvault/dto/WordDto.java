package com.cengizhanyavuz.wordvault.dto;

import com.cengizhanyavuz.wordvault.model.Word;

public record WordDto(
        Long id,
        String tr,
        String en,
        String it,
        String alm,
        Boolean isApproved,
        Integer proficiencyLevel,
        Integer personalProficiencyLevel
) {
    public static WordDto convert(Word word) {
        return new WordDto(
                word.getId(),
                word.getTr(),
                word.getEn(),
                word.getIt(),
                word.getAlm(),
                word.getIsApproved(),
                word.getProficiencyLevel(),
                word.getPersonalProficiencyLevel()
        );
    }
}
