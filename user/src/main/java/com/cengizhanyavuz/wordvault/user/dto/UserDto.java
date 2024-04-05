package com.cengizhanyavuz.wordvault.user.dto;

import com.cengizhanyavuz.wordvault.user.model.Gender;
import com.cengizhanyavuz.wordvault.user.model.User;

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