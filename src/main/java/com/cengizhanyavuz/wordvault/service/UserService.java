package com.cengizhanyavuz.wordvault.service;

import com.cengizhanyavuz.wordvault.dto.UserDto;
import com.cengizhanyavuz.wordvault.dto.request.UserUpdateRequest;
import com.cengizhanyavuz.wordvault.model.BaseService;
import com.cengizhanyavuz.wordvault.model.user.User;
import com.cengizhanyavuz.wordvault.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    protected Integer getUserElo() {
       return getCurrentUser().getElo();
    }


    public UserDto getUser() {
        return UserDto.convert(getCurrentUser());
    }

    public UserDto updateUser(UserUpdateRequest request) {
        User user = getCurrentUser();
        user.setName(request.name());
        user.setUsername(request.username());
        user.setSurname(request.surname());
        user.setPassword(request.password());
        user.setAge(request.age());
        user.setGender(request.gender());
        return UserDto.convert(userRepository.save(user));
    }


}
