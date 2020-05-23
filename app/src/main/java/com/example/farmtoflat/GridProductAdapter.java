package com.example.farmtoflat;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GridProductAdapter extends BaseAdapter {

    List<HorizontalProductScrollModel_today> mHorizontalProductScrollModel_todayList;

    public GridProductAdapter(List<HorizontalProductScrollModel_today> horizontalProductScrollModel_todayList) {
        mHorizontalProductScrollModel_todayList = horizontalProductScrollModel_todayList;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;
         ImageView productImage;
         TextView productTitle;
         TextView productWeight;
         TextView productPrice;
        if(convertView == null){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout1,null);
            itemView.setElevation(0);
            itemView.setBackgroundColor(Color.parseColor("#64C34A"));
            productImage = itemView.findViewById(R.id.horizontal_scroll_layout1_product_image);
            productTitle = itemView.findViewById(R.id.horizontal_scroll_layout1_product_title);
            productWeight = itemView.findViewById(R.id.horizontal_scroll_layout1_product_weight);
            productPrice = itemView.findViewById(R.id.horizontal_scroll_layout1_product_price);

            productImage.setImageResource(mHorizontalProductScrollModel_todayList.get(position).getProductImage());
            productTitle.setText(mHorizontalProductScrollModel_todayList.get(position).getProductTitle());
            productWeight.setText(mHorizontalProductScrollModel_todayList.get(position).getProductWeight());
            productPrice.setText(mHorizontalProductScrollModel_todayList.get(position).getProductPrice());
        }else{
            itemView = convertView;
        }
        return itemView;
    }
}
