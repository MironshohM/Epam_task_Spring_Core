package org.example.service;

import jakarta.annotation.PostConstruct;
import org.example.component.TraineeStorage;
import org.example.dto.TraineeDto;
import org.example.model.Trainee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TraineeServiceImpl implements TraineeService {
    private static final Logger logger = LoggerFactory.getLogger(TraineeServiceImpl.class);

    private final TraineeStorage traineeStorage;
    private final GeneratorService generatorService;
    private final Map<String, Trainee> traineeMap = new HashMap<>();

    @Autowired
    public TraineeServiceImpl(TraineeStorage traineeStorage, GeneratorService generatorService) {
        this.traineeStorage = traineeStorage;
        this.generatorService = generatorService;
    }

    @PostConstruct
    public void initializeTrainees() {
        logger.info("Initializing trainees...");
        try {
            for (TraineeDto dto : traineeStorage.getTraineeDtos().values()) {
                String username = generatorService.generateUsername(dto.getFirstName(), dto.getLastName());
                String password = generatorService.generatePassword();
                Trainee trainee = new Trainee(dto.getUserId(), dto.getFirstName(), dto.getLastName(),
                        username, password, dto.isActive(), dto.getDateOfBirth(), dto.getAddress());
                traineeMap.put(username, trainee);
                logger.info("Trainee initialized: {}", username);
            }
            logger.info("Trainees initialization completed. Total: {}", traineeMap.size());
        } catch (Exception e) {
            logger.error("Error during trainee initialization", e);
        }
    }

    public Trainee getTrainee(String username) {
        logger.info("Fetching trainee with username: {}", username);
        return traineeMap.get(username);
    }

    public void createTrainee(Trainee trainee) {
        traineeMap.put(trainee.getUsername(), trainee);
        logger.info("Trainee created: {}", trainee.getUsername());
    }

    public void updateTrainee(String username, Trainee updatedTrainee) {
        if (traineeMap.containsKey(username)) {
            traineeMap.put(username, updatedTrainee);
            logger.info("Trainee updated: {}", username);
        } else {
            logger.warn("Attempted to update non-existent trainee: {}", username);
        }
    }

    public void deleteTrainee(String username) {
        if (traineeMap.remove(username) != null) {
            logger.info("Trainee deleted: {}", username);
        } else {
            logger.warn("Attempted to delete non-existent trainee: {}", username);
        }
    }
}
