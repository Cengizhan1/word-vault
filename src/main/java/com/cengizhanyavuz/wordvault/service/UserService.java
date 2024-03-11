package com.cengizhanyavuz.wordvault.service;

import com.cengizhanyavuz.wordvault.dto.UserDto;
import com.cengizhanyavuz.wordvault.dto.request.UserUpdateRequest;
import com.cengizhanyavuz.wordvault.exception.UsernameAlreadyExistsException;
import com.cengizhanyavuz.wordvault.model.user.User;
import com.cengizhanyavuz.wordvault.repository.UserRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.cengizhanyavuz.wordvault.constants.PointConstants.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDto getUser() {
        return UserDto.convert(getCurrentUser());
    }

    public UserDto updateUser(UserUpdateRequest request) {
        checkUserNameExists(request.username());
        User user = getCurrentUser();
        user.setName(request.name());
        user.setUsername(request.username());
        user.setSurname(request.surname());
        user.setPassword(request.password());
        user.setAge(request.age());
        user.setGender(request.gender());
        return UserDto.convert(userRepository.save(user));
    }


    // Protected methods
    @Async
    protected void updateUserElo(int correctAnswers, int wrongAnswers) {
        User user = getCurrentUser();
        user.setElo(user.getElo() + (correctAnswers - wrongAnswers) * USER_POINTS_TO_INCREASED);
        userRepository.save(user);
    }

    protected User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authentication.getName()).orElseThrow((
                () -> new UsernameNotFoundException("User not found with username: " + getCurrentUser().getUsername()))
        );
    }

    protected void checkUserNameExists(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException("Username already exists for : " + username);
        }
    }

}
