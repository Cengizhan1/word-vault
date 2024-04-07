package com.cengizhanyavuz.wordvault.main.dto.request;

import com.cengizhanyavuz.wordvault.main.model.user.Gender;

public record RegisterRequest(
        String name,
        String username,
        String surname,
        String password,
        Integer age,
        Gender gender
){
}