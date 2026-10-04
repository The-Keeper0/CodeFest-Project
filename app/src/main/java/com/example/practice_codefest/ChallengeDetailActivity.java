package com.example.practice_codefest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practice_codefest.data.ChallengeRepository;
import com.example.practice_codefest.data.ProgressManager;
import com.example.practice_codefest.model.Challenge;
import com.example.practice_codefest.model.ChallengeStatus;
import com.example.practice_codefest.util.ImpactStars;

public class ChallengeDetailActivity extends AppCompatActivity {

    public static final String EXTRA_CHALLENGE_ID = "extra_challenge_id";

    private Challenge challenge;
    private ProgressManager progressManager;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_challenge_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int id = getIntent().getIntExtra(EXTRA_CHALLENGE_ID, -1);
        challenge = ChallengeRepository.getById(id);
        if (challenge == null) {
            finish();
            return;
        }
        progressManager = new ProgressManager(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Challenge Details");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        TextView tvTitle = findViewById(R.id.tvDetailTitle);
        TextView tvCategory = findViewById(R.id.tvDetailCategory);
        TextView tvImpact = findViewById(R.id.tvDetailImpact);
        TextView tvTime = findViewById(R.id.tvDetailTime);
        TextView tvDescription = findViewById(R.id.tvDetailDescription);

        tvTitle.setText(challenge.getTitle());
        tvCategory.setText(challenge.getCategory().getTitle());
        tvImpact.setText(ImpactStars.build(challenge.getImpactScore()));
        tvTime.setText("Estimated time: " + challenge.getEstimatedMinutes() + " min");
        tvDescription.setText(challenge.getDescription());

        RadioGroup rgStatus = findViewById(R.id.rgStatus);

        ChallengeStatus current = progressManager.getStatus(challenge.getId());
        if (current == ChallengeStatus.COMPLETED) {
            rgStatus.check(R.id.rbCompleted);
        } else if (current == ChallengeStatus.IN_PROGRESS) {
            rgStatus.check(R.id.rbInProgress);
        } else {
            rgStatus.check(R.id.rbNotStarted);
        }

        rgStatus.setOnCheckedChangeListener((group, checkedId) -> {
            ChallengeStatus newStatus;
            if (checkedId == R.id.rbCompleted) {
                newStatus = ChallengeStatus.COMPLETED;
            } else if (checkedId == R.id.rbInProgress) {
                newStatus = ChallengeStatus.IN_PROGRESS;
            } else {
                newStatus = ChallengeStatus.NOT_STARTED;
            }
            progressManager.setStatus(challenge.getId(), newStatus);
        });

        // 4. Save / bookmark
        btnSave = findViewById(R.id.btnSave);
        updateSaveButton();
        btnSave.setOnClickListener(v -> {
            boolean nowSaved = !progressManager.isSaved(challenge.getId());
            progressManager.setSaved(challenge.getId(), nowSaved);
            updateSaveButton();
            Toast.makeText(this,
                    nowSaved ? "Challenge saved" : "Removed from saved",
                    Toast.LENGTH_SHORT).show();
        });
    }

    private void updateSaveButton() {
        if (progressManager.isSaved(challenge.getId())) {
            btnSave.setText("Remove from Saved");
        } else {
            btnSave.setText("Save Challenge");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
