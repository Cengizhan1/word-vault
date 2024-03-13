package com.cengizhanyavuz.wordvault.dto.test;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record TestFinishRequestDto(
        @Size(min = 10, max = 10,message = "Size of answers must be 10")
        List<String> answers
) {
}
