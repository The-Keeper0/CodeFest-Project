package com.example.practice_codefest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice_codefest.R;
import com.example.practice_codefest.data.ProgressManager;
import com.example.practice_codefest.model.Challenge;
import com.example.practice_codefest.util.ImpactStars;

import java.util.List;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ViewHolder> {

    public interface OnChallengeClickListener {
        void onChallengeClick(Challenge challenge);
    }

    private final List<Challenge> challenges;
    private final ProgressManager progressManager;
    private final OnChallengeClickListener listener;

    public ChallengeAdapter(List<Challenge> challenges,
                            ProgressManager progressManager,
                            OnChallengeClickListener listener) {
        this.challenges = challenges;
        this.progressManager = progressManager;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_challenge, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Challenge challenge = challenges.get(position);

        holder.tvTitle.setText(challenge.getTitle());
        holder.tvImpact.setText(ImpactStars.build(challenge.getImpactScore()));
        holder.tvInfo.setText(challenge.getEstimatedMinutes() + " min");
        holder.tvStatus.setText(progressManager.getStatus(challenge.getId()).getLabel());
        holder.itemView.setOnClickListener(v -> listener.onChallengeClick(challenge));
    }

    @Override
    public int getItemCount() {
        return challenges.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvImpact;
        final TextView tvInfo;
        final TextView tvStatus;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvChallengeTitle);
            tvImpact = itemView.findViewById(R.id.tvChallengeImpact);
            tvInfo = itemView.findViewById(R.id.tvChallengeInfo);
            tvStatus = itemView.findViewById(R.id.tvChallengeStatus);
        }
    }
}
