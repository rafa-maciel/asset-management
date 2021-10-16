package com.rmaciel.assetmanagement.location.config;

import com.rmaciel.academy.core.models.Location;
import com.rmaciel.academy.core.models.Model;
import com.rmaciel.academy.core.models.UserAccount;
import com.rmaciel.academy.core.models.UserAccountProfile;
import com.rmaciel.academy.core.repositories.LocationRepository;
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
    private final LocationRepository locationRepository;
    private final UserAccountRepository userAccountRepository;


    public DatabaseInitRunner(LocationRepository modelRepository, UserAccountRepository userAccountRepository) {
        this.locationRepository = modelRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(
                this.userAccountRepository.save(new UserAccount("rafa@rafa.com", "Rafa", "rafa123", true, UserAccountProfile.ADMIN)).toString()
        );
        log.info(
                this.locationRepository.save(new Location("Itaquera", "Armazem de Peças")).toString()
        );
        log.info(
                this.locationRepository.save(new Location("Mooca", "Loja de revenda")).toString()
        );
    }
}
