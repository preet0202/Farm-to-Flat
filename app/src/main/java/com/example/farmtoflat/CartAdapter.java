package com.example.farmtoflat;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    private List<CartItemModel> mCartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        mCartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {

        switch(mCartItemModelList.get(position).getType()){
            case 0:
                return CartItemModel.CART_ITEM;
            case 1:
                return CartItemModel.TOTAL_AMOUNT;

            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch(viewType){
            case CartItemModel.CART_ITEM:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
                return new CartItemViewholder(view);

            case CartItemModel.TOTAL_AMOUNT:
                View carttotalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_amount_layout,parent,false);
                return new CartTotalAmountViewHolder(carttotalView);

            default:
                return  null;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch(mCartItemModelList.get(position).getType()){

            case CartItemModel.CART_ITEM:
                int resource = mCartItemModelList.get(position).getProductImage();
                String title = mCartItemModelList.get(position).getProductTitle();
                int freecoupons = mCartItemModelList.get(position).getFreecoupons();
                String productprice = mCartItemModelList.get(position).getProductprice();
                int offersApplied = mCartItemModelList.get(position).getOffersapllied();
                ((CartItemViewholder)viewHolder).setItemDetails(resource,title,freecoupons,productprice,offersApplied);
                break;

            case CartItemModel.TOTAL_AMOUNT:
                String totalItems = mCartItemModelList.get(position).getTotalitems();
                String totalItemsPrice = mCartItemModelList.get(position).gettotalItemPric();
                String deleveryPrice = mCartItemModelList.get(position).getDeleveryPrice();
                String savedAmount = mCartItemModelList.get(position).getSavedAmount();
                String totalAmount = mCartItemModelList.get(position).getTotalAmount();

                ((CartTotalAmountViewHolder)viewHolder).setTotalAmount(totalItems,totalItemsPrice,deleveryPrice,savedAmount,totalAmount);
        }
    }

    @Override
    public int getItemCount() {
        return mCartItemModelList.size();
    }

    public class CartItemViewholder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private ImageView freecouponicon;
        private TextView productTitle;
        private TextView freeCoupons;
        private TextView productPrice;
        private TextView offersApplied;
        private TextView couponsApplied;
        private TextView productQuantity;

        public CartItemViewholder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            freecouponicon = itemView.findViewById(R.id.free_coupon_icon);
            freeCoupons = itemView.findViewById(R.id.free_coupon);
            productPrice= itemView.findViewById(R.id.product_price);
            offersApplied = itemView.findViewById(R.id.offers_applied);
            couponsApplied = itemView.findViewById(R.id.coupons_applied);
            productQuantity = itemView.findViewById(R.id.product_qnt);
        }
        private void setItemDetails(int resource,String title,int freeCouponsNo ,String productPriceText,int offersAppliedNo ){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if(freeCouponsNo >0) {
                freecouponicon.setVisibility(View.VISIBLE);
                freeCoupons.setVisibility(View.VISIBLE);
                if (freeCouponsNo == 1) {
                    freeCoupons.setText("Free " + freeCouponsNo + "Coupon");
                } else {
                    freeCoupons.setText("Free " + freeCouponsNo + "Coupons");
                }
            }
            else {
                freecouponicon.setVisibility(View.INVISIBLE);
                freeCoupons.setVisibility(View.INVISIBLE);
            }
            productPrice.setText(productPriceText);
            if(offersAppliedNo > 0){
                offersApplied.setVisibility(View.VISIBLE);
                offersApplied.setText(offersAppliedNo+ " Offers Applied");
            }
            else{
                offersApplied.setVisibility(View.VISIBLE);
            }

            productQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog quantityDialog = new Dialog(itemView.getContext());
                    quantityDialog.setContentView(R.layout.quantity_dialog);
                    quantityDialog.setCancelable(false);
                    final EditText quantityNo = quantityDialog.findViewById(R.id.quantity_number);
                    Button cancelBtn = quantityDialog.findViewById(R.id.cancel_btn);
                    Button okBtn = quantityDialog.findViewById(R.id.ok_btn);

                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            quantityDialog.dismiss();
                        }
                    });
                    okBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            productQuantity.setText("Qty: " + quantityNo.getText());
                            quantityDialog.dismiss();
                        }
                    });
                    quantityDialog.show();
                }
            });
        }
    }

    public class CartTotalAmountViewHolder extends RecyclerView.ViewHolder{

        private TextView totalItems;
        private TextView totalItemsPrice;
        private TextView deliveryPrice;
        private TextView totalAmount;
        private TextView savedAmount;

        public CartTotalAmountViewHolder(@NonNull View itemView) {
            super(itemView);
            totalItems = itemView.findViewById(R.id.total_items);
            totalItemsPrice = itemView.findViewById(R.id.total_items_price);
            deliveryPrice = itemView.findViewById(R.id.delivery_price);
            totalAmount = itemView.findViewById(R.id.total_cart_amount);
            savedAmount = itemView.findViewById(R.id.saved_amount);
        }

        private void setTotalAmount(String totalItemtext,String totalItemPricetext,String deliveryPriceText,String savedAmountText,String totalAmountText){
            totalItems.setText(totalItemtext);
            totalItemsPrice.setText(totalItemPricetext);
            deliveryPrice.setText(deliveryPriceText);
            totalAmount.setText(totalAmountText);
            savedAmount.setText(savedAmountText);
        }
    }
}
