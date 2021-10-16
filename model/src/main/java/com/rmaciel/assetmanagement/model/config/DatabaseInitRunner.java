package com.rmaciel.assetmanagement.model.config;

import com.rmaciel.academy.core.models.*;
import com.rmaciel.academy.core.repositories.ModelRepository;
import com.rmaciel.academy.core.repositories.UserAccountRepository;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ToString
public class DatabaseInitRunner implements CommandLineRunner {
    private final ModelRepository modelRepository;
    private final UserAccountRepository userAccountRepository;


    public DatabaseInitRunner(ModelRepository modelRepository, UserAccountRepository userAccountRepository) {
        this.modelRepository = modelRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(
                this.userAccountRepository.save(new UserAccount("rafa@rafa.com", "Rafa", "rafa123", true, UserAccountProfile.ADMIN)).toString()
        );
        log.info(
                this.modelRepository.save(new Model("Elitebook 840 G3", "HP", "Notebook")).toString()
        );
        log.info(
                this.modelRepository.save(new Model("Ultraboook 7480", "DELL", "Notebook")).toString()
        );
    }
}
