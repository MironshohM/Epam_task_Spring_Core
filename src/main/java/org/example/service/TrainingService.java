package org.example.service;

import org.example.model.Training;

public interface TrainingService {
    void createTraining(Training training);

    Training getTraining(String trainingId);

    void initializeTrainings();
}
