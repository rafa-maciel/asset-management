package com.rmaciel.assetmanagement.useraccount.config;

import com.rmaciel.academy.core.models.UserAccount;
import com.rmaciel.academy.core.models.UserAccountProfile;
import com.rmaciel.academy.core.repositories.UserAccountRepository;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ToString
public class DatabaseInitRunner implements CommandLineRunner {
    private final UserAccountRepository userAccountRepository;

    public DatabaseInitRunner(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(
                this.userAccountRepository.save(new UserAccount("rafa@rafa.com", "Rafa", "rafa123", true, UserAccountProfile.ADMIN)).toString()
        );
        log.info(
                this.userAccountRepository.save(new UserAccount("lu@rafa.com", "Luciana", "rafa123", true, UserAccountProfile.RH)).toString()
        );
        log.info(
                this.userAccountRepository.save(new UserAccount("gabriel@rafa.com", "Gabriel", "rafa123", true, UserAccountProfile.IT)).toString()
        );
    }
}
