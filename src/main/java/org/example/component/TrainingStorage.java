package org.example.component;

import jakarta.annotation.PostConstruct;
import org.example.dto.TrainingDto;
import org.example.model.Training;
import org.example.model.TrainingType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
@Component
public class TrainingStorage {

    private final Map<String, TrainingDto> trainingDtos = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(TrainingStorage.class);

    @Value("${data.trainings.file}")
    private String filePath;

    public Map<String, TrainingDto> getTrainingDtos() {
        return trainingDtos;
    }

    @PostConstruct
    public void loadTrainingsFromFile() {
        logger.info("Starting to load trainings from file: {}", filePath);
        try {
            Files.lines(Path.of(filePath)).forEach(line -> {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    TrainingDto trainingDto = new TrainingDto(
                            parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], Integer.parseInt(parts[6])
                    );
                    trainingDtos.put(parts[0], trainingDto);
                    logger.debug("Loaded training: {}", trainingDto);
                } else {
                    logger.warn("Skipping invalid line (wrong number of parts): {}", line);
                }
            });
            logger.info("Successfully loaded {} trainings.", trainingDtos.size());
        } catch (IOException e) {
            logger.error("Error reading training data file: {}", filePath, e);
            throw new RuntimeException("Error reading training data file: " + filePath, e);
        }
    }
}
