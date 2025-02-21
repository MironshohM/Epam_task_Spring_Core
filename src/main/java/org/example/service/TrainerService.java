package org.example.service;

import org.example.model.Trainee;
import org.example.model.Trainer;

public interface TrainerService {
    void createTrainer(Trainer trainer);

    void updateTrainer(String username,Trainer updatedTrainer);

    Trainer getTrainer(String username);

    void initializeTrainers();
}
