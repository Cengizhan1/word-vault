package com.cengizhanyavuz.wordvault.main.dto;

import com.cengizhanyavuz.wordvault.main.model.user.Gender;
import com.cengizhanyavuz.wordvault.main.model.user.User;

public record UserDto(
        Long id,
        String name,
        String surname,
        String username,
        Integer age,
        Gender gender,
        Integer elo

) {
    public static UserDto convert(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getAge(),
                user.getGender(),
                user.getElo()
        );
    }
}