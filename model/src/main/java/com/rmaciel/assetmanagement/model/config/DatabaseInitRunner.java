package com.rmaciel.assetmanagement.model.config;

import com.rmaciel.academy.core.models.User;
import com.rmaciel.academy.core.models.UserAccount;
import com.rmaciel.academy.core.models.UserAccountProfile;
import com.rmaciel.academy.core.models.UserStatus;
import com.rmaciel.academy.core.repositories.UserAccountRepository;
import com.rmaciel.academy.core.repositories.UserRepository;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ToString
public class DatabaseInitRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    public DatabaseInitRunner(UserAccountRepository userAccountRepository, UserRepository userRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(
                this.userAccountRepository.save(new UserAccount("rafa@rafa.com", "Rafa", "rafa123", true, UserAccountProfile.ADMIN)).toString()
        );
        log.info(
                this.userRepository.save(new User("Tony Stark", 33251, "Engenharia", UserStatus.ACTIVE, "Incontrolavel")).toString()
        );
        log.info(
                this.userRepository.save(new User("Peter Park", 33251, "Inovação", UserStatus.ACTIVE, "constantes atrasos")).toString()
        );
    }

    private final UserAccountRepository userAccountRepository;
}
