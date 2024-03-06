package com.cengizhanyavuz.wordvault.dto.request;

import com.cengizhanyavuz.wordvault.model.Word;

public record WordUpdateRequest(
        Long id,
        String tr,
        String en,
        String it,
        String alm
) {}
