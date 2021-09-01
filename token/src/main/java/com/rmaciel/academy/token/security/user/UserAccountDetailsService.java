package com.rmaciel.academy.token.security.user;

import com.rmaciel.academy.core.models.UserAccount;
import com.rmaciel.academy.core.repositories.UserAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountDetailsService implements UserDetailsService {
    private final UserAccountRepository userAccountRepository;

    public UserAccountDetailsService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> userAccountOptional = userAccountRepository.findByEmail(username);
        if (!userAccountOptional.isPresent())
            throw new UsernameNotFoundException("User not found with this username: " + username);

        return new UserAccountDetails(userAccountOptional.get());
    }
}
