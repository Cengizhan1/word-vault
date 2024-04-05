package com.cengizhanyavuz.wordvault.user.dto;

import com.cengizhanyavuz.wordvault.user.model.Gender;

public record RegisterRequest(
        String name,
        String username,
        String surname,
        String password,
        Integer age,
        Gender gender
){
}