package org.example.model;

public class Trainer extends User {
    private String userId;
    private String specialization;

    public Trainer(String userId, String firstName, String lastName, String username, String password, boolean isActive, String specialization) {
        super(firstName, lastName, username, password, isActive);
        this.userId = userId;
        this.specialization = specialization;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
