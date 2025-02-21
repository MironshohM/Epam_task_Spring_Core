package org.example.service;

import jakarta.annotation.PostConstruct;
import org.example.component.TrainingStorage;
import org.example.dto.TrainingDto;
import org.example.model.Training;
import org.example.model.TrainingType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class TrainingServiceImpl implements TrainingService {
    private static final Logger logger = LoggerFactory.getLogger(TrainingServiceImpl.class);

    private final TrainingStorage trainingStorage;
    private final Map<String, Training> trainingMap = new HashMap<>();

    @Autowired
    public TrainingServiceImpl(TrainingStorage trainingStorage) {
        this.trainingStorage = trainingStorage;
    }

    @PostConstruct
    public void initializeTrainings() {
        logger.info("Initializing training sessions...");
        try {
            for (TrainingDto dto : trainingStorage.getTrainingDtos().values()) {
                Training training = new Training(
                        dto.getTrainingId(),
                        dto.getTraineeId(),
                        dto.getTrainerId(),
                        TrainingType.valueOf(dto.getTrainingType()),
                        dto.getTrainingName(),
                        dto.getTrainingDate(),
                        dto.getTrainingDuration()
                );
                trainingMap.put(training.getTrainingId(), training);
                logger.info("Training initialized: {} ({})", training.getTrainingName(), training.getTrainingId());
            }
            logger.info("Training initialization completed. Total: {}", trainingMap.size());
        } catch (Exception e) {
            logger.error("Error during training initialization", e);
        }
    }

    public void createTraining(Training training) {
        trainingMap.put(training.getTrainingId(), training);
        logger.info("New training created: {} ({})", training.getTrainingName(), training.getTrainingId());
    }

    public Training getTraining(String trainingId) {
        logger.info("Fetching training with ID: {}", trainingId);
        Training training = trainingMap.get(trainingId);
        if (training == null) {
            logger.warn("Training not found: {}", trainingId);
        }
        return training;
    }
}

