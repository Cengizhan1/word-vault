package com.cengizhanyavuz.wordvault.dto.request;

import com.cengizhanyavuz.wordvault.model.Gender;

public record RegisterRequest(
        String name,
        String username,
        String surname,
        String password,
        Integer age,
        Gender gender
){
}