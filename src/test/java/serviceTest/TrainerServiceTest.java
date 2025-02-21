package serviceTest;

import org.example.component.TrainerStorage;
import org.example.dto.TrainerDto;
import org.example.model.Trainer;
import org.example.service.GeneratorService;
import org.example.service.TrainerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainerServiceTest {

    @Mock
    private TrainerStorage trainerStorage;

    @Mock
    private GeneratorService generatorService;

    @InjectMocks
    private TrainerServiceImpl trainerService;

    private Map<String, TrainerDto> trainerDtoMap;

    @BeforeEach
    void setUp() {
        // Sample trainer data
        trainerDtoMap = new HashMap<>();
        TrainerDto trainerDto = new TrainerDto("1", "Jane", "Smith", true, "Java");
        trainerDtoMap.put("1", trainerDto);

        // Mock storage returning sample trainers
        when(trainerStorage.getTrainerDtos()).thenReturn(trainerDtoMap);

        // Mock username and password generation
        when(generatorService.generateUsername("Jane", "Smith")).thenReturn("jane.smith");
        when(generatorService.generatePassword()).thenReturn("secureTrainerPass");

        // Initialize trainers
        trainerService.initializeTrainers();
    }

    @Test
    void testInitializeTrainers() {
        Trainer trainer = trainerService.getTrainer("jane.smith");
        assertNotNull(trainer);
        assertEquals("Jane", trainer.getFirstName());
        assertEquals("Smith", trainer.getLastName());
        assertEquals("secureTrainerPass", trainer.getPassword());
    }

    @Test
    void testCreateTrainer() {
        Trainer newTrainer = new Trainer("2", "Alice", "Johnson", "alice.johnson", "password456", true, "Python");
        trainerService.createTrainer(newTrainer);
        assertEquals(newTrainer, trainerService.getTrainer("alice.johnson"));
    }

    @Test
    void testUpdateTrainer() {
        Trainer updatedTrainer = new Trainer("1", "Jane", "Smith", "jane.smith", "newTrainerPass", true, "Spring Boot");
        trainerService.updateTrainer("jane.smith", updatedTrainer);
        assertEquals("newTrainerPass", trainerService.getTrainer("jane.smith").getPassword());
    }

    @Test
    void testGetTrainer() {
        Trainer trainer = trainerService.getTrainer("jane.smith");
        assertNotNull(trainer);
        assertEquals("Jane", trainer.getFirstName());
    }
}
