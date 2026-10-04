package com.example.practice_codefest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice_codefest.adapter.ChallengeAdapter;
import com.example.practice_codefest.data.ChallengeRepository;
import com.example.practice_codefest.data.ProgressManager;
import com.example.practice_codefest.model.Challenge;
import com.example.practice_codefest.model.ChallengeCategory;

import java.util.ArrayList;
import java.util.List;

public class ChallengeListActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY = "extra_category";
    public static final String EXTRA_SAVED_ONLY = "extra_saved_only";

    private final List<Challenge> items = new ArrayList<>();

    private ChallengeAdapter adapter;
    private ProgressManager progressManager;
    private TextView tvEmpty;
    private boolean savedOnly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_challenge_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.challengeListRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        progressManager = new ProgressManager(this);
        tvEmpty = findViewById(R.id.tvEmpty);
        savedOnly = getIntent().getBooleanExtra(EXTRA_SAVED_ONLY, false);

        String title;
        if (savedOnly) {
            title = "Saved Challenges";
        } else {
            String categoryName = getIntent().getStringExtra(EXTRA_CATEGORY);
            if (categoryName == null) {
                finish();
                return;
            }
            ChallengeCategory category = ChallengeCategory.valueOf(categoryName);
            title = category.getTitle();
            items.addAll(ChallengeRepository.getByCategory(category));
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView rv = findViewById(R.id.rvChallenges);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ChallengeAdapter(
                items,
                progressManager,
                challenge -> {
                    Intent intent = new Intent(this, ChallengeDetailActivity.class);
                    intent.putExtra(ChallengeDetailActivity.EXTRA_CHALLENGE_ID,
                            challenge.getId());
                    startActivity(intent);
                });
        rv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (savedOnly) {
            items.clear();
            items.addAll(progressManager.getSavedChallenges());
            tvEmpty.setVisibility(items.isEmpty() ? View.VISIBLE : View.GONE);
        }

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
