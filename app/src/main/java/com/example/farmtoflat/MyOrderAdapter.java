package com.example.farmtoflat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.Viewholder> {



    List<MyOrderItemModel> mMyOrderItemModelList;

    public MyOrderAdapter(List<MyOrderItemModel> myOrderItemModelList) {
        mMyOrderItemModelList = myOrderItemModelList;
    }

    @NonNull
    @Override
    public MyOrderAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.Viewholder holder, int position) {

        int resource = mMyOrderItemModelList.get(position).getProductImage();
        int rating = mMyOrderItemModelList.get(position).getRating();
        String title = mMyOrderItemModelList.get(position).getProductTitle();
        String deliveryStaus = mMyOrderItemModelList.get(position).getDeliveryStatus();
        holder.setData(resource,title,deliveryStaus,rating);

    }

    @Override
    public int getItemCount() {
        return mMyOrderItemModelList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{


        private ImageView productImage;
        private ImageView orderIndicator;
        private TextView productTitle;
        private TextView deliveryStatus;
        private LinearLayout rateNowContainer;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            orderIndicator  = itemView.findViewById(R.id.order_indicator);
            deliveryStatus = itemView.findViewById(R.id.order_delivered_date);
            rateNowContainer = itemView.findViewById(R.id.rate_now_container);
        }
        private void setData(int resource,String title, String deliveryDate,int rating){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if(deliveryDate.equals("Cancelled")){
                orderIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.cancelled)));
            }else{
                orderIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.colorPrimary)));
            }
            deliveryStatus.setText(deliveryDate);
            setRating(rating);
            for(int i= 0;i< rateNowContainer.getChildCount();i++){
                final int startposition = i;
                rateNowContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        setRating(startposition);
                        // Code here executes on main thread after user presses button
                    }
                });
            }
        }
        private  void setRating (int starpos){
            for(int i= 0;i< rateNowContainer.getChildCount();i++){
                ImageView starBtn = (ImageView)rateNowContainer.getChildAt(i);
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
                if(i <= starpos){
                    starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
                }
            }
        }
    }
}
