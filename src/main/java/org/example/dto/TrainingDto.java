package org.example.dto;

import org.example.model.TrainingType;

public class TrainingDto {
    private String trainingId;
    private String traineeId;
    private String trainerId;
    private String trainingTypeNum;
    private String trainingName;
    private String trainingDate;
    private int trainingDuration;

    public TrainingDto(String trainingId, String traineeId, String trainerId, String trainingTypeNum, String trainingName, String trainingDate, int trainingDuration) {
        this.trainingId = trainingId;
        this.traineeId = traineeId;
        this.trainerId = trainerId;
        this.trainingTypeNum = trainingTypeNum;
        this.trainingName = trainingName;
        this.trainingDate = trainingDate;
        this.trainingDuration = trainingDuration;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
    }

    public String getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(String traineeId) {
        this.traineeId = traineeId;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    @Override
    public String toString() {
        return "TrainingDto{" +
                "trainingId='" + trainingId + '\'' +
                ", traineeId='" + traineeId + '\'' +
                ", trainerId='" + trainerId + '\'' +
                ", trainingTypeNum='" + trainingTypeNum + '\'' +
                ", trainingName='" + trainingName + '\'' +
                ", trainingDate='" + trainingDate + '\'' +
                ", trainingDuration=" + trainingDuration +
                '}';
    }

    public String getTrainingType() {
        return trainingTypeNum;
    }

    public void setTrainingType(String trainingTypeNum) {
        this.trainingTypeNum = trainingTypeNum;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(String trainingDate) {
        this.trainingDate = trainingDate;
    }

    public int getTrainingDuration() {
        return trainingDuration;
    }

    public void setTrainingDuration(int trainingDuration) {
        this.trainingDuration = trainingDuration;
    }
}
