package com.example.practice_codefest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice_codefest.R;

import java.util.List;

public class BadgeAdapter extends RecyclerView.Adapter<BadgeAdapter.ViewHolder> {

    public static class Badge {
        private final String title;
        private final String description;
        private final boolean unlocked;

        public Badge(String title, String description, boolean unlocked) {
            this.title = title;
            this.description = description;
            this.unlocked = unlocked;
        }

        public boolean isUnlocked() {
            return unlocked;
        }
    }

    private final List<Badge> badges;

    public BadgeAdapter(List<Badge> badges) {
        this.badges = badges;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_badge, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Badge badge = badges.get(position);

        holder.tvTitle.setText(badge.title);
        holder.tvDesc.setText(badge.description);
        holder.tvState.setText(badge.unlocked ? "Earned" : "Locked");
        holder.itemView.setAlpha(badge.unlocked ? 1f : 0.4f);
    }

    @Override
    public int getItemCount() {
        return badges.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDesc;
        final TextView tvState;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvBadgeTitle);
            tvDesc = itemView.findViewById(R.id.tvBadgeDesc);
            tvState = itemView.findViewById(R.id.tvBadgeState);
        }
    }
}
