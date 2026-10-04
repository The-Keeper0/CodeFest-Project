package com.example.practice_codefest.model;

public enum ChallengeCategory {
    STRENGTH("Strength Training"),
    CARDIO("Cardio & Endurance"),
    FLEXIBILITY("Flexibility & Mobility"),
    CORE("Core & Balance"),
    COMMUNITY("Community Action");

    private final String title;

    ChallengeCategory(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
