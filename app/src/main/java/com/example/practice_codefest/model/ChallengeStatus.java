package com.example.practice_codefest.model;

public enum ChallengeStatus {
    NOT_STARTED("Not Started"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed");

    private final String label;

    ChallengeStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
