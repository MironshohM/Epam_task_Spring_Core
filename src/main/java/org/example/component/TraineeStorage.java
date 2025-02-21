package org.example.component;

import jakarta.annotation.PostConstruct;
import org.example.dto.TraineeDto;
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
public class TraineeStorage {

    private static final Logger logger = LoggerFactory.getLogger(TraineeStorage.class);

    private final Map<String, TraineeDto> traineeDtos = new HashMap<>();

    @Value("${data.trainees.file}")
    private String filePath;

    public Map<String, TraineeDto> getTraineeDtos() {
        return traineeDtos;
    }
    public TraineeStorage() {
        System.out.println("TraineeStorage Bean Initialized!");
    }

    @PostConstruct
    public void loadTraineesFromFile() {
        logger.info("Starting to load trainees from file: {}", filePath);
        try {
            Files.lines(Path.of(filePath)).forEach(line -> {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    String address=parts[5]+" "+parts[6]+" "+parts[7];
                    TraineeDto  traineeDto = new TraineeDto(parts[0], parts[1], parts[2],Boolean.parseBoolean(parts[3]),parts[4],address);
                    traineeDtos.put(parts[0], traineeDto);
                    logger.debug("Loaded trainee: {}", traineeDto);
                } else {
                    logger.warn("Skipping invalid line (wrong number of parts): {}", line);
                }
            });
            logger.info("Successfully loaded {} trainees.", traineeDtos.size());
        } catch (IOException e) {
            logger.error("Error reading trainee data file: {}", filePath, e);
            throw new RuntimeException("Error reading trainee data file: " + filePath, e);
        }
    }
}
