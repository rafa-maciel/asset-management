package com.rmaciel.assetmanagement.asset.config;

import com.rmaciel.academy.core.models.*;
import com.rmaciel.academy.core.repositories.*;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        log.info(
                this.userAccountRepository.save(new UserAccount("rafa@rafa.com", "Rafa", "rafa123", true, UserAccountProfile.ADMIN)).toString()
        );
        User owner = userRepository.save(new User("Robert Wilson", 116542, "Marketing", UserStatus.ACTIVE, ""));
        Location location = locationRepository.save(new Location("Vila Madalena", "Escritório Central"));
        Model model = modelRepository.save(new Model("Elitebook 8470p", "Dell", "Notebook"));

        log.info(owner.toString());
        log.info(location.toString());
        log.info(model.toString());

        log.info(
                this.assetRepository.save(new Asset(owner, location, model, AssetStatus.ACTIVE)).toString()
        );
        log.info(
                this.assetRepository.save(new Asset(owner, location, model, AssetStatus.BROKEN)).toString()
        );
    }
}
