package com.example.farmtoflat;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MycartFragment extends Fragment {

    public MycartFragment() {
        // Required empty public constructor
    }

    private RecyclerView cartItemRecylerView;
    private Button continueBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mycart, container, false);

        cartItemRecylerView = view.findViewById(R.id.cart_items_recyclerView);
        continueBtn = view.findViewById(R.id.cart_continue_button);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemRecylerView.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.drawable.veg1,"Carrot","Rs. 200/-",1,2,0,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.veg1,"Carrot","Rs. 200/-",1,0,1,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.veg1,"Carrot","Rs. 200/-",1,2,2,0));
        cartItemModelList.add(new CartItemModel(1,"Price (3 Items)","Rs. 600/-","Free","Rs. 50/-","Rs. 600/-"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        cartItemRecylerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();


        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent = new Intent(getContext(), DeliveryActivity.class);
                getContext().startActivity(deliveryIntent);
            }
        });
        return view;
    }
}
