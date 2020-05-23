package com.example.farmtoflat;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

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
        viewHolder.setCategoryName(name);
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

        public void setCategoryIcon() {
            //todo:set categoryIcon here
        }

        private void setCategoryName(String name){
            categoryName.setText(name);
        }

    }

}
