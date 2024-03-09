package com.cengizhanyavuz.wordvault.service;

import com.cengizhanyavuz.wordvault.model.BaseService;
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




}
