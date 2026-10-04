package com.example.practice_codefest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice_codefest.adapter.CategoryAdapter;
import com.example.practice_codefest.data.ProgressManager;
import com.example.practice_codefest.model.ChallengeCategory;

import java.util.Arrays;

public class CategoryListFragment extends Fragment {

    private CategoryAdapter adapter;
    private ProgressManager progressManager;
    private TextView tvSavedCount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressManager = new ProgressManager(requireContext());

        // Saved Challenges card
        tvSavedCount = view.findViewById(R.id.tvSavedCount);
        view.findViewById(R.id.cardSaved).setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), ChallengeListActivity.class);
            intent.putExtra(ChallengeListActivity.EXTRA_SAVED_ONLY, true);
            startActivity(intent);
        });

        RecyclerView rv = view.findViewById(R.id.rvCategories);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new CategoryAdapter(
                Arrays.asList(ChallengeCategory.values()),
                progressManager,
                category -> {
                    Intent intent = new Intent(requireContext(), ChallengeListActivity.class);
                    intent.putExtra(ChallengeListActivity.EXTRA_CATEGORY, category.name());
                    startActivity(intent);
                });
        rv.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        if (tvSavedCount != null) {
            tvSavedCount.setText(progressManager.getSavedCount() + " saved");
        }
    }
}
