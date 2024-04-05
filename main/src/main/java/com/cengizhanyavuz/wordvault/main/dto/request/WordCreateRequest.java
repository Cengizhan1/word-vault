package com.cengizhanyavuz.wordvault.main.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WordCreateRequest(
        @NotNull @NotBlank
        String tr,
        @NotNull @NotBlank
        String en,
        String it,
        String alm
) {}
