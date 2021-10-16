package com.rmaciel.assetmanagement.model.config;

import com.rmaciel.academy.core.models.*;
import com.rmaciel.academy.core.repositories.ModelRepository;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ToString
public class DatabaseInitRunner implements CommandLineRunner {
    private final ModelRepository modelRepository;

    public DatabaseInitRunner(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(
                this.modelRepository.save(new Model("Elitebook 840 G3", "HP", "Notebook")).toString()
        );
        log.info(
                this.modelRepository.save(new Model("Ultraboook 7480", "DELL", "Notebook")).toString()
        );
    }
}
