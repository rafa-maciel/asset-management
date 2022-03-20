package com.rmaciel.assetmanagement.contract.config;

import com.rmaciel.academy.core.models.*;
import com.rmaciel.academy.core.repositories.*;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("DEV")
@Component
@Slf4j
@ToString
public class DatabaseInitRunner implements CommandLineRunner {
    private final AssetRepository assetRepository;
    private final UserAccountRepository userAccountRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final ModelRepository modelRepository;


    public DatabaseInitRunner(AssetRepository assetRepository, UserAccountRepository userAccountRepository,
                              UserRepository userRepository, LocationRepository locationRepository,
                              ModelRepository modelRepository) {

        this.assetRepository = assetRepository;
        this.userAccountRepository = userAccountRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
