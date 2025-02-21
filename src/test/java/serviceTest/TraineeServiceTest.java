package serviceTest;

import org.example.component.TraineeStorage;
import org.example.model.Trainee;
import org.example.dto.TraineeDto;
import org.example.service.TraineeServiceImpl;
import org.example.service.GeneratorService;
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
class TraineeServiceTest {

    @Mock
    private TraineeStorage traineeStorage;

    @Mock
    private GeneratorService generatorService;

    @InjectMocks
    private TraineeServiceImpl traineeService;

    private Map<String, TraineeDto> traineeDtoMap;

    @BeforeEach
    void setUp() {
        // Sample trainee data
        traineeDtoMap = new HashMap<>();
        TraineeDto traineeDto = new TraineeDto("1", "John", "Doe", true, "1995-05-15", "123 Street");
        traineeDtoMap.put("1", traineeDto);

        // Mock storage returning sample trainees
        when(traineeStorage.getTraineeDtos()).thenReturn(traineeDtoMap);

        // Mock username and password generation
        when(generatorService.generateUsername("John", "Doe")).thenReturn("john.doe");
        when(generatorService.generatePassword()).thenReturn("securePass");

        // Initialize trainees
        traineeService.initializeTrainees();
    }

    @Test
    void testInitializeTrainees() {
        Trainee trainee = traineeService.getTrainee("john.doe");
        assertNotNull(trainee);
        assertEquals("John", trainee.getFirstName());
        assertEquals("Doe", trainee.getLastName());
        assertEquals("securePass", trainee.getPassword());
    }

    @Test
    void testGetTrainee() {
        Trainee trainee = traineeService.getTrainee("john.doe");
        assertNotNull(trainee);
    }

    @Test
    void testCreateTrainee() {
        Trainee newTrainee = new Trainee("2", "Alice", "Smith", "alice.smith", "password123", true, "1998-07-20", "456 Avenue");
        traineeService.createTrainee(newTrainee);
        assertEquals(newTrainee, traineeService.getTrainee("alice.smith"));
    }

    @Test
    void testUpdateTrainee() {
        Trainee updatedTrainee = new Trainee("1", "John", "Doe", "john.doe", "newPass", true, "1995-05-15", "789 Road");
        traineeService.updateTrainee("john.doe", updatedTrainee);
        assertEquals("newPass", traineeService.getTrainee("john.doe").getPassword());
    }

    @Test
    void testDeleteTrainee() {
        traineeService.deleteTrainee("john.doe");
        assertNull(traineeService.getTrainee("john.doe"));
    }
}

