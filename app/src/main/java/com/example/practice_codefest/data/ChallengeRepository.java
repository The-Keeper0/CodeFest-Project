package com.example.practice_codefest.data;

import com.example.practice_codefest.model.Challenge;
import com.example.practice_codefest.model.ChallengeCategory;

import java.util.ArrayList;
import java.util.List;

public class ChallengeRepository {

    private static final List<Challenge> CHALLENGES = new ArrayList<>();

    // Format: id, title, description, estimatedMinutes, impactScore (1-5), category
    static {
        // Strength Training
        CHALLENGES.add(new Challenge(1, "Push-Up Progress",
                "Do push-ups every day and add a few more reps each session until you reach your goal.",
                10, 4, ChallengeCategory.STRENGTH));
        CHALLENGES.add(new Challenge(2, "Squat Streak",
                "Complete a set of bodyweight squats every day and keep your streak going.",
                10, 4, ChallengeCategory.STRENGTH));
        CHALLENGES.add(new Challenge(3, "Bodyweight Basics",
                "A beginner circuit of squats, lunges, push-ups, and planks using only your body weight.",
                20, 4, ChallengeCategory.STRENGTH));

        // Cardio & Endurance
        CHALLENGES.add(new Challenge(4, "Ten-Minute Jog",
                "Go for a steady 10-minute jog at a comfortable pace.",
                10, 4, ChallengeCategory.CARDIO));
        CHALLENGES.add(new Challenge(5, "Stair Climb Challenge",
                "Climb stairs for a few rounds to raise your heart rate and work your legs.",
                15, 5, ChallengeCategory.CARDIO));
        CHALLENGES.add(new Challenge(6, "Jump Rope Blast",
                "Jump rope in short bursts with rests in between for a fast full-body workout.",
                10, 5, ChallengeCategory.CARDIO));

        // Flexibility & Mobility
        CHALLENGES.add(new Challenge(7, "Morning Stretch",
                "Start your day with a gentle full-body stretch routine.",
                10, 2, ChallengeCategory.FLEXIBILITY));
        CHALLENGES.add(new Challenge(8, "Full Split Goal",
                "Practice daily stretches that slowly work toward a full split.",
                20, 3, ChallengeCategory.FLEXIBILITY));
        CHALLENGES.add(new Challenge(9, "Desk Break Loosener",
                "Loosen your neck, shoulders, and back with quick stretches between work sessions.",
                5, 2, ChallengeCategory.FLEXIBILITY));

        // Core & Balance
        CHALLENGES.add(new Challenge(10, "Plank a Day",
                "Hold a plank every day and add a few seconds as you get stronger.",
                5, 4, ChallengeCategory.CORE));
        CHALLENGES.add(new Challenge(11, "Balance Board Basics",
                "Practice standing and shifting your weight on a balance board to build stability.",
                10, 3, ChallengeCategory.CORE));
        CHALLENGES.add(new Challenge(12, "Sit-Up Streak",
                "Do a set of sit-ups every day to strengthen your core and keep your streak alive.",
                10, 3, ChallengeCategory.CORE));

        // Community Action
        CHALLENGES.add(new Challenge(13, "Group Workout Day",
                "Invite friends or family and exercise together for a fun group session.",
                45, 4, ChallengeCategory.COMMUNITY));
        CHALLENGES.add(new Challenge(14, "Fitness Buddy Check-In",
                "Check in with a fitness buddy to share progress and keep each other motivated.",
                10, 2, ChallengeCategory.COMMUNITY));
        CHALLENGES.add(new Challenge(15, "Step Challenge Sunday",
                "Join others in a Sunday walking challenge and aim for a shared step goal.",
                60, 3, ChallengeCategory.COMMUNITY));
    }

    private ChallengeRepository() {
    }

    public static List<Challenge> getAll() {
        return new ArrayList<>(CHALLENGES);
    }

    public static List<Challenge> getByCategory(ChallengeCategory category) {
        List<Challenge> result = new ArrayList<>();
        for (Challenge c : CHALLENGES) {
            if (c.getCategory() == category) {
                result.add(c);
            }
        }
        return result;
    }

    public static Challenge getById(int id) {
        for (Challenge c : CHALLENGES) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}
