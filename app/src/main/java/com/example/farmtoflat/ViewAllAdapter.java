package com.example.farmtoflat;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

    private List<ViewAllModel> viewAllModelList;
    private Boolean wishList;


    public ViewAllAdapter(List<ViewAllModel> viewAllModelList, Boolean wishList) {
        this.viewAllModelList = viewAllModelList;
        this.wishList = wishList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item_layout,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int resource = viewAllModelList.get(position).getProductImage();
        String title = viewAllModelList.get(position).getProductTitle();
        int freecoupens = viewAllModelList.get(position).getFreeCoupens();
        String rating = viewAllModelList.get(position).getRating();
        int totalRatings = viewAllModelList.get(position).getTotalRatings();
        String productPrice = viewAllModelList.get(position).getProductPrice();
        String cuttedPrice = viewAllModelList.get(position).getCuttedPrice();
        String paymentMethod = viewAllModelList.get(position).getPaymentMethod();

        holder.setData(resource, title, freecoupens, rating, totalRatings, productPrice, cuttedPrice, paymentMethod);


    }

    @Override
    public int getItemCount() {
        return viewAllModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView productImage, coupenIcon, deleteBtn;
        private TextView productTitle, freeCoupens, productPrice, cuttedPrice, paymentMethod, rating, totalRatings;
        private View priceCut;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            freeCoupens = itemView.findViewById(R.id.free_coupon);
            coupenIcon = itemView.findViewById(R.id.coupon_icon);
            rating = itemView.findViewById(R.id.product_rating);
            totalRatings = itemView.findViewById(R.id.total_ratings);
            priceCut = itemView.findViewById(R.id.price_cut);
            productPrice = itemView.findViewById(R.id.product_price);
            cuttedPrice = itemView.findViewById(R.id.cutted_price);
            paymentMethod = itemView.findViewById(R.id.payment_method);
            deleteBtn = itemView.findViewById(R.id.delete_btn);

        }
        private void setData(int resource, String title, int freeCoupensNo, String averageRate, int totalRatingsNo, String price, String cuttedPriceValue, String payMethod){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if (freeCoupensNo != 0) {
                coupenIcon.setVisibility(View.VISIBLE);
                if (freeCoupensNo == 1) {
                    freeCoupens.setText("free " + freeCoupensNo + " coupon");
                } else {
                    freeCoupens.setText("free " + freeCoupensNo + " coupons");
                }
            } else {
                coupenIcon.setVisibility(View.INVISIBLE);
                freeCoupens.setVisibility(View.INVISIBLE);
            }
            rating.setText(averageRate);
            totalRatings.setText(totalRatingsNo+"(ratings)");
            productPrice.setText(price);
            cuttedPrice.setText(cuttedPriceValue);
            paymentMethod.setText(payMethod);


            if (wishList){
                deleteBtn.setVisibility(View.VISIBLE);
            } else {
                deleteBtn.setVisibility(View.GONE);
            }
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "delete", Toast.LENGTH_SHORT).show();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //43
                    Toast.makeText(itemView.getContext(), "Product View", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}
