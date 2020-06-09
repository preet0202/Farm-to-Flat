package com.example.farmtoflat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RewardsAdapter extends RecyclerView.Adapter<RewardsAdapter.ViewHolder> {

    private List<RewardModel> rewardModelList;

    public RewardsAdapter(List<RewardModel> rewardModelList) {
        this.rewardModelList = rewardModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rewards_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String title = rewardModelList.get(position).getTitle();
        String date = rewardModelList.get(position).getExpiryDate();
        String body = rewardModelList.get(position).getCoupenBody();
        holder.setData(title, date, body);

    }

    @Override
    public int getItemCount() {
        return rewardModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView expiryDate;
        private TextView coupenBody;
        private TextView offerTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            expiryDate = itemView.findViewById(R.id.reward_date);
            coupenBody = itemView.findViewById(R.id.offer_details);
            offerTitle = itemView.findViewById(R.id.offer_title);
        }
        private void setData(String title, String date, String body) {
            expiryDate.setText(date);
            coupenBody.setText(body);
            offerTitle.setText(title);

        }
    }
}
