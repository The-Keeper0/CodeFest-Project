package com.example.practice_codefest.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.practice_codefest.model.Challenge;
import com.example.practice_codefest.model.ChallengeCategory;
import com.example.practice_codefest.model.ChallengeStatus;

import java.util.ArrayList;
import java.util.List;

public class ProgressManager {

    private static final String PREFS_NAME = "fittrack_progress";
    private static final String KEY_STATUS_PREFIX = "status_";
    private static final String KEY_SAVED_PREFIX = "saved_";

    private final SharedPreferences prefs;

    public ProgressManager(Context context) {
        prefs = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public ChallengeStatus getStatus(int challengeId) {
        String saved = prefs.getString(KEY_STATUS_PREFIX + challengeId,
                ChallengeStatus.NOT_STARTED.name());
        try {
            return ChallengeStatus.valueOf(saved);
        } catch (IllegalArgumentException e) {
            return ChallengeStatus.NOT_STARTED;
        }
    }

    public void setStatus(int challengeId, ChallengeStatus status) {
        prefs.edit().putString(KEY_STATUS_PREFIX + challengeId, status.name()).apply();
    }

    public boolean isSaved(int challengeId) {
        return prefs.getBoolean(KEY_SAVED_PREFIX + challengeId, false);
    }

    public void setSaved(int challengeId, boolean saved) {
        prefs.edit().putBoolean(KEY_SAVED_PREFIX + challengeId, saved).apply();
    }

    public List<Challenge> getSavedChallenges() {
        List<Challenge> result = new ArrayList<>();
        for (Challenge c : ChallengeRepository.getAll()) {
            if (isSaved(c.getId())) {
                result.add(c);
            }
        }
        return result;
    }

    public int getSavedCount() {
        return getSavedChallenges().size();
    }

    public int getCompletedCount() {
        int count = 0;
        for (Challenge c : ChallengeRepository.getAll()) {
            if (getStatus(c.getId()) == ChallengeStatus.COMPLETED) {
                count++;
            }
        }
        return count;
    }

    public int getCompletedCount(ChallengeCategory category) {
        int count = 0;
        for (Challenge c : ChallengeRepository.getByCategory(category)) {
            if (getStatus(c.getId()) == ChallengeStatus.COMPLETED) {
                count++;
            }
        }
        return count;
    }
}
