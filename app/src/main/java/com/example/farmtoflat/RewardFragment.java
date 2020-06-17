package com.example.farmtoflat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class RewardFragment extends Fragment {

    public RewardFragment() {

    }

    private RecyclerView rewardsRV;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_reward, container, false);
        rewardsRV = view.findViewById(R.id.my_reward_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rewardsRV.setLayoutManager(layoutManager);

        List<RewardModel> rewardModelList = new ArrayList<>();
        rewardModelList.add(new RewardModel("till 29th September 2020","Get 20% off on shopping above 5000","Discount"));
        rewardModelList.add(new RewardModel("till 29th September 2020","Get 20% off on shopping above 5000","Rewards"));
        rewardModelList.add(new RewardModel("till 29th September 2020","Get 20% off on shopping above 5000","Cashback"));
        rewardModelList.add(new RewardModel("till 29th September 2020","Get 20% off on shopping above 5000","Buy 1 Get 2"));
        rewardModelList.add(new RewardModel("till 29th September 2020","Get 20% off on shopping above 5000","Discount"));
        rewardModelList.add(new RewardModel("till 29th September 2020","Get 20% off on shopping above 5000","Discount"));
        rewardModelList.add(new RewardModel("till 29th September 2020","Get 20% off on shopping above 5000","Discount"));
        rewardModelList.add(new RewardModel("till 29th September 2020","Get 20% off on shopping above 5000","Discount"));
        rewardModelList.add(new RewardModel("till 29th September 2020","Get 20% off on shopping above 5000","Rewards"));
        rewardModelList.add(new RewardModel("till 29th September 2020","Get 20% off on shopping above 5000","Cashback"));
        rewardModelList.add(new RewardModel("till 29th September 2020","Get 20% off on shopping above 5000","Buy 1 Get 2"));

        RewardsAdapter rewardsAdapter = new RewardsAdapter(rewardModelList);
        rewardsRV.setAdapter(rewardsAdapter);
        rewardsAdapter.notifyDataSetChanged();
        return view;
    }
}