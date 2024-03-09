package com.cengizhanyavuz.wordvault.dto.request;

import com.cengizhanyavuz.wordvault.model.user.Gender;

public record UserUpdateRequest(
        String name,
        String username,
        String surname,
        String password,
        Integer age,
        Gender gender
){
}