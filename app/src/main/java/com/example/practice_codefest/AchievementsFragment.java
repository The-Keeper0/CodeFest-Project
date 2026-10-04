package com.example.practice_codefest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice_codefest.adapter.BadgeAdapter;
import com.example.practice_codefest.data.ChallengeRepository;
import com.example.practice_codefest.data.ProgressManager;
import com.example.practice_codefest.model.ChallengeCategory;

import java.util.ArrayList;
import java.util.List;

public class AchievementsFragment extends Fragment {

    private final List<BadgeAdapter.Badge> badges = new ArrayList<>();
    private BadgeAdapter adapter;
    private ProgressManager progressManager;
    private TextView tvProgress;
    private TextView tvBadgeCount;
    private ProgressBar pbOverall;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_achievements, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressManager = new ProgressManager(requireContext());
        tvProgress = view.findViewById(R.id.tvProgress);
        tvBadgeCount = view.findViewById(R.id.tvBadgeCount);
        pbOverall = view.findViewById(R.id.pbOverall);

        RecyclerView rv = view.findViewById(R.id.rvBadges);
        rv.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        adapter = new BadgeAdapter(badges);
        rv.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        int total = ChallengeRepository.getAll().size();
        int completed = progressManager.getCompletedCount();

        // Overall progress
        tvProgress.setText(completed + " / " + total + " challenges completed");
        pbOverall.setMax(total);
        pbOverall.setProgress(completed);

        badges.clear();

        badges.add(new BadgeAdapter.Badge("First Step",
                "Complete 1 challenge", completed >= 1));
        badges.add(new BadgeAdapter.Badge("Getting Warm",
                "Complete 5 challenges", completed >= 5));
        badges.add(new BadgeAdapter.Badge("Halfway There",
                "Complete 8 challenges", completed >= 8));
        badges.add(new BadgeAdapter.Badge("Fitness Champion",
                "Complete all " + total + " challenges", completed == total));

        for (ChallengeCategory category : ChallengeRepository.getAll().isEmpty()
                ? new ChallengeCategory[0] : ChallengeCategory.values()) {
            int categoryTotal = ChallengeRepository.getByCategory(category).size();
            int categoryDone = progressManager.getCompletedCount(category);
            badges.add(new BadgeAdapter.Badge(category.getTitle() + " Star",
                    "Complete all " + categoryTotal + " " + category.getTitle() + " challenges",
                    categoryDone == categoryTotal));
        }

        int earned = 0;
        for (BadgeAdapter.Badge badge : badges) {
            if (badge.isUnlocked()) {
                earned++;
            }
        }
        tvBadgeCount.setText("Badges: " + earned + " / " + badges.size() + " earned");

        adapter.notifyDataSetChanged();
    }
}
