package com.rmaciel.assetmanagement.asset.config;

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
        log.info(
                this.userAccountRepository.save(new UserAccount("rafa@rafa.com", "Rafa", "rafa123", true, UserAccountProfile.ADMIN)).toString()
        );
        User owner = userRepository.save(new User("Tony Stark", 33251, "Engenharia", UserStatus.ACTIVE));
        Location location = this.locationRepository.save(new Location("Itaquera", "Armazem de Peças"));
        Model model = modelRepository.save(new Model("Elitebook 840 G3", "HP", "Notebook"));

        log.info(owner.toString());
        log.info(location.toString());
        log.info(model.toString());

        log.info(
                this.locationRepository.save(new Location("Mooca", "Loja de revenda")).toString()
        );
        log.info(
                this.modelRepository.save(new Model("Ultraboook 7480", "DELL", "Notebook")).toString()
        );
        log.info(
                this.userRepository.save(new User("Peter Park", 33251, "Inovação", UserStatus.ACTIVE)).toString()
        );

        log.info(
                this.assetRepository.save(new Asset(owner, location, model, "PE66531" ,AssetStatus.ACTIVE)).toString()
        );
        log.info(
                this.assetRepository.save(new Asset(owner, location, model,"LT5454641", AssetStatus.BROKEN)).toString()
        );
    }
}
