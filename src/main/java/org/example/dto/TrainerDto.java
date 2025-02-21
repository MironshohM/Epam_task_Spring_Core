package org.example.dto;

public class TrainerDto {
    private String userId;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private String specialization;

    public TrainerDto(String userId, String firstName, String lastName, boolean isActive, String specialization) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.specialization = specialization;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "TrainerDto{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isActive=" + isActive +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
