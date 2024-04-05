package com.cengizhanyavuz.wordvault.quiz.dto;

import jakarta.validation.constraints.Size;

import java.util.List;

public record TestFinishRequestDto(
        @Size(min = 5, max = 5,message = "Size of answers must be 5")
        List<String> answers
) {
}
