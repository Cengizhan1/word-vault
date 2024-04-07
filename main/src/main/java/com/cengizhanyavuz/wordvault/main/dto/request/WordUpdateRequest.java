package com.cengizhanyavuz.wordvault.main.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WordUpdateRequest(
        @NotNull(message = "Id cannot be null")
        Long id,
        @NotNull @NotBlank
        String tr,
        @NotNull @NotBlank
        String en,
        String it,
        String alm
) {}
