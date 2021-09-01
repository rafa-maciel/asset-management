package com.rmaciel.academy.core.services;

import com.rmaciel.academy.core.repositories.UserAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

}
