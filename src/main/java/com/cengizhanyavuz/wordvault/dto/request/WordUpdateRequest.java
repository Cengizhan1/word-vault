package com.cengizhanyavuz.wordvault.dto.request;

import com.cengizhanyavuz.wordvault.model.Word;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WordUpdateRequest(
        Long id,
        @NotNull @NotBlank
        String tr,
        @NotNull @NotBlank
        String en,
        String it,
        String alm
) {}
