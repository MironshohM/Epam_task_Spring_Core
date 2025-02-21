package serviceTest;

import org.example.component.TrainingStorage;
import org.example.dto.TrainingDto;
import org.example.model.Training;
import org.example.model.TrainingType;
import org.example.service.TrainingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainingServiceTest {

    @Mock
    private TrainingStorage trainingStorage;

    @InjectMocks
    private TrainingServiceImpl trainingService;

    private Map<String, TrainingDto> trainingDtoMap;

    @BeforeEach
    void setUp() {
        // Sample training data with String dates
        trainingDtoMap = new HashMap<>();
        trainingDtoMap.put("1", new TrainingDto("1", "3", "1", "STRENGTH_TRAINING", "Beginner Strength Training", "2024-02-15", 60));
        trainingDtoMap.put("2", new TrainingDto("2", "5", "2", "CARDIO", "HIIT Cardio Blast", "2024-02-16", 45));
        trainingDtoMap.put("3", new TrainingDto("3", "7", "3", "BODYBUILDING", "Bodybuilding Basics", "2024-02-17", 90));
        trainingDtoMap.put("4", new TrainingDto("4", "2", "4", "YOGA", "Yoga for Beginners", "2024-02-18", 60));
        trainingDtoMap.put("5", new TrainingDto("5", "6", "5", "CROSSFIT", "Full-Body CrossFit", "2024-02-19", 75));
        trainingDtoMap.put("6", new TrainingDto("6", "8", "6", "PILATES", "Advanced Pilates", "2024-02-20", 50));
        trainingDtoMap.put("7", new TrainingDto("7", "9", "7", "PERSONAL_TRAINING", "Personal Training Session", "2024-02-21", 30));
        trainingDtoMap.put("8", new TrainingDto("8", "1", "8", "ATHLETIC_PERFORMANCE", "Athletic Performance Boost", "2024-02-22", 80));
        trainingDtoMap.put("9", new TrainingDto("9", "4", "9", "MMA_KICKBOXING", "MMA & Kickboxing Drills", "2024-02-23", 70));
        trainingDtoMap.put("10", new TrainingDto("10", "10", "10", "WEIGHT_LOSS", "Weight Loss & Nutrition Plan", "2024-02-24", 40));

        // Mock storage returning sample trainings
        when(trainingStorage.getTrainingDtos()).thenReturn(trainingDtoMap);

        // Initialize trainings
        trainingService.initializeTrainings();
    }

    @Test
    void testInitializeTrainings() {
        Training training = trainingService.getTraining("1");
        assertNotNull(training);
        assertEquals("Beginner Strength Training", training.getTrainingName());
        assertEquals(TrainingType.STRENGTH_TRAINING, training.getTrainingType());
        assertEquals(60, training.getTrainingDuration());

        training = trainingService.getTraining("10");
        assertNotNull(training);
        assertEquals("Weight Loss & Nutrition Plan", training.getTrainingName());
        assertEquals(TrainingType.WEIGHT_LOSS, training.getTrainingType());
        assertEquals(40, training.getTrainingDuration());
    }

    @Test
    void testCreateTraining() {
        Training newTraining = new Training("11", "11", "11", TrainingType.PILATES, "Pilates for Beginners", "2024-03-01", 55);
        trainingService.createTraining(newTraining);
        assertEquals(newTraining, trainingService.getTraining("11"));
    }

    @Test
    void testGetTraining() {
        Training training = trainingService.getTraining("5");
        assertNotNull(training);
        assertEquals("Full-Body CrossFit", training.getTrainingName());
        assertEquals(TrainingType.CROSSFIT, training.getTrainingType());
        assertEquals(75, training.getTrainingDuration());
    }

    @Test
    void testGetNonExistentTraining() {
        Training training = trainingService.getTraining("999");
        assertNull(training);
    }
}
