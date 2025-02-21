package org.example.component;

import jakarta.annotation.PostConstruct;
import org.example.dto.TrainerDto;
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
public class TrainerStorage {

    private static final Logger logger = LoggerFactory.getLogger(TrainerStorage.class);

    private final Map<String, TrainerDto> trainerDtos = new HashMap<>();

    @Value("${data.trainers.file}")
    private String filePath;

    public Map<String, TrainerDto> getTrainerDtos() {
        return trainerDtos;
    }

    @PostConstruct
    public void loadTrainersFromFile() {
        logger.info("Starting to load trainers from file: {}", filePath);
        try {
            Files.lines(Path.of(filePath)).forEach(line -> {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    TrainerDto trainerDto = new TrainerDto(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[3]), parts[4]);
                    trainerDtos.put(parts[0], trainerDto);
                    logger.debug("Loaded trainer: {}", trainerDto);
                }else {
                    logger.warn("Skipping invalid line (wrong number of parts): {}", line);
                }
            });
            logger.info("Successfully loaded {} trainers.", trainerDtos.size());
        } catch (IOException e) {
            logger.error("Error reading trainer data file: {}", filePath, e);
            throw new RuntimeException("Error reading trainer data file: " + filePath, e);
        }
    }
}
