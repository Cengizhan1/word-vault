package com.cengizhanyavuz.wordvault.dto.test;

import java.time.LocalDateTime;
import java.util.List;

public record TestFinishRequestDto(
    List<String> answers,
    LocalDateTime lastAnsweredDate
) {
}
