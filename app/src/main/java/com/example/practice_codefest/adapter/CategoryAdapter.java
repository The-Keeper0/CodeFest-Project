package com.example.practice_codefest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice_codefest.R;
import com.example.practice_codefest.data.ChallengeRepository;
import com.example.practice_codefest.data.ProgressManager;
import com.example.practice_codefest.model.ChallengeCategory;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    public interface OnCategoryClickListener {
        void onCategoryClick(ChallengeCategory category);
    }

    private final List<ChallengeCategory> categories;
    private final ProgressManager progressManager;
    private final OnCategoryClickListener listener;

    public CategoryAdapter(List<ChallengeCategory> categories,
                           ProgressManager progressManager,
                           OnCategoryClickListener listener) {
        this.categories = categories;
        this.progressManager = progressManager;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChallengeCategory category = categories.get(position);
        int total = ChallengeRepository.getByCategory(category).size();
        int done = progressManager.getCompletedCount(category);

        holder.tvName.setText(category.getTitle());
        holder.tvProgress.setText(done + " / " + total + " completed");
        holder.itemView.setOnClickListener(v -> listener.onCategoryClick(category));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvName;
        final TextView tvProgress;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvCategoryName);
            tvProgress = itemView.findViewById(R.id.tvCategoryProgress);
        }
    }
}
