package org.example.service;

import org.example.model.Trainee;

public interface TraineeService {

    void createTrainee(Trainee trainee);

    void updateTrainee(String username,Trainee updatedTrainee);

    void deleteTrainee(String username);

    Trainee getTrainee(String username);

    void initializeTrainees();

}
