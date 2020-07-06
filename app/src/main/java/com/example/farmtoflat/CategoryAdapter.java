package com.example.farmtoflat;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
//import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private static final String TAG = "llllllllllllllllllllll";

    private List<CategoryModel> mCategoryModel;

    public CategoryAdapter(List<CategoryModel> categoryModel) {
        mCategoryModel = categoryModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        String icon = mCategoryModel.get(position).getCategoryLink();
        String name = mCategoryModel.get(position).getCategoryName();
        Log.d(TAG, "Loadig image" +icon+ " "+ name);
        viewHolder.setCategory(name);
        viewHolder.setCategoryIcon(icon);
    }

    @Override
    public int getItemCount() {
        return mCategoryModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView categoryIcon;
        private TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.category_icon);
            categoryName = itemView.findViewById(R.id.category_name);
        }

        public void setCategoryIcon(String iconUrl) {
            if (!(iconUrl.equals("null"))) {
                try {
                    Glide.with(itemView.getContext()).load(iconUrl).into(categoryIcon);
                }catch(Exception e){
                    Log.d(TAG, "setCategory: "+ e);
                }
                //Picasso.get().load(iconUrl).error(R.mipmap.baseline_home_black_18dp).into(categoryIcon);
            }
        }

        private void setCategory(final String name){
           try {
               categoryName.setText(name);
               itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       if (name == "More") {
                           Intent categoryIntent = new Intent(itemView.getContext(), MoreActivity.class);
                           categoryIntent.putExtra("CategoryName", name);
                           itemView.getContext().startActivity(categoryIntent);
                       } else if (name == "Order Now") {
//                        String dial = "";  //enter the number to call
//                        itemView.getContext().startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", dial, null)));
                           itemView.getContext().startActivity(new Intent(itemView.getContext(), OrderNowActivity.class));
                       }
                   }
               });
           }catch (Exception e){
               //Toast.makeText(this, "Initialized YouTube Player successful", Toast.LENGTH_LONG).show();
               //Toast.makeText(CategoryAdapter.this, "Flat can't be empty", Toast.LENGTH_LONG).show();
               Log.d(TAG, "setCategory: e");
           }

        }

    }
}
