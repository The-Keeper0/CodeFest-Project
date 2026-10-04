package com.example.practice_codefest.model;

public class Challenge {
    private final int id;
    private final String title;
    private final String description;
    private final int estimatedMinutes;
    private final int impactScore;
    private final ChallengeCategory category;

    public Challenge(int id, String title, String description,
                     int estimatedMinutes, int impactScore,
                     ChallengeCategory category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.estimatedMinutes = estimatedMinutes;
        this.impactScore = impactScore;
        this.category = category;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getEstimatedMinutes() { return estimatedMinutes; }
    public int getImpactScore() { return impactScore; }
    public ChallengeCategory getCategory() { return category; }
}
