package com.example.farmtoflat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HorizontalProductScrollAdapter_today extends RecyclerView.Adapter<HorizontalProductScrollAdapter_today.ViewHolder> {

    private List<HorizontalProductScrollModel_today> mHorizontalProductScrollModel_todays;

    public HorizontalProductScrollAdapter_today(List<HorizontalProductScrollModel_today> horizontalProductScrollModel_todays) {
        mHorizontalProductScrollModel_todays = horizontalProductScrollModel_todays;
    }



    @NonNull
    @Override
    public HorizontalProductScrollAdapter_today.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout1,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductScrollAdapter_today.ViewHolder holder, int position) {

        String  resource = mHorizontalProductScrollModel_todays.get(position).getProductImage();
        String title = mHorizontalProductScrollModel_todays.get(position).getProductTitle();
        String weight = mHorizontalProductScrollModel_todays.get(position).getProductWeight();
        String price = mHorizontalProductScrollModel_todays.get(position).getProductPrice();

        holder.setProductImage(resource);
        holder.setProductTitle(title);
        holder.setProductWeight(weight);
        holder.setProductPrice(price);

    }

    @Override
    public int getItemCount() {

        if(mHorizontalProductScrollModel_todays.size()>6){
            return 6;
        }
        else {
            return mHorizontalProductScrollModel_todays.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        private TextView productWeight;
        private TextView productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.horizontal_scroll_layout1_product_image);
            productTitle = itemView.findViewById(R.id.horizontal_scroll_layout1_product_title);
            productWeight = itemView.findViewById(R.id.horizontal_scroll_layout1_product_weight);
            productPrice = itemView.findViewById(R.id.horizontal_scroll_layout1_product_price);
        }

        private void setProductImage(String resource){
            final String TAG = "llllllllllllllllllllll";
            Log.d(TAG, "Loadig image  " +resource);
            Picasso.get().load(resource).error(R.mipmap.baseline_home_black_18dp).into(productImage);
           // Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.mipmap.baseline_home_black_18dp)).into(productImage);
        }
        private void setProductTitle(String s){
            productTitle.setText(s);
        }
        private void setProductWeight(String s){
            productWeight.setText(s);
        }
        private void setProductPrice(String s){
            productPrice.setText(s);
        }


    }
}

