package org.example.service;

import jakarta.annotation.PostConstruct;
import org.example.component.TrainerStorage;
import org.example.dto.TrainerDto;
import org.example.model.Trainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TrainerServiceImpl implements TrainerService {
    private static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

    private final TrainerStorage trainerStorage;
    private final GeneratorService generatorService;
    private final Map<String, Trainer> trainerMap = new HashMap<>();

    @Autowired
    public TrainerServiceImpl(TrainerStorage trainerStorage, GeneratorService generatorService) {
        this.trainerStorage = trainerStorage;
        this.generatorService = generatorService;
    }

    @PostConstruct
    public void initializeTrainers() {
        logger.info("Initializing trainers...");
        try {
            for (TrainerDto dto : trainerStorage.getTrainerDtos().values()) {
                String username = generatorService.generateUsername(dto.getFirstName(), dto.getLastName());
                String password = generatorService.generatePassword();

                Trainer trainer = new Trainer(dto.getUserId(), dto.getFirstName(), dto.getLastName(),
                        username, password, dto.isActive(), dto.getSpecialization());

                trainerMap.put(username, trainer);
                logger.info("Trainer initialized: {}", username);
            }
            logger.info("Trainers initialization completed. Total: {}", trainerMap.size());
        } catch (Exception e) {
            logger.error("Error during trainer initialization", e);
        }
    }

    public void createTrainer(Trainer trainer) {
        trainerMap.put(trainer.getUsername(), trainer);
        logger.info("Trainer created: {}", trainer.getUsername());
    }

    public void updateTrainer(String username, Trainer updatedTrainer) {
        if (trainerMap.containsKey(username)) {
            trainerMap.put(username, updatedTrainer);
            logger.info("Trainer updated: {}", username);
        } else {
            logger.warn("Attempted to update non-existent trainer: {}", username);
        }
    }

    public Trainer getTrainer(String username) {
        logger.info("Fetching trainer with username: {}", username);
        return trainerMap.get(username);
    }
}
