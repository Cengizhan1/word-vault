package com.cengizhanyavuz.wordvault.dto.request;

import com.cengizhanyavuz.wordvault.model.Word;

public record WordCreateRequest(
        String tr,
        String en,
        String it,
        String alm
) {}
