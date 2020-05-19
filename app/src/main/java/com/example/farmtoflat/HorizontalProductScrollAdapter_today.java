package com.example.farmtoflat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        int  resource = mHorizontalProductScrollModel_todays.get(position).getProductImage();
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
        return mHorizontalProductScrollModel_todays.size();
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

        private void setProductImage(int resource){
            productImage.setImageResource(resource);
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

