package com.example.farmtoflat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.api.LogDescriptor;
//import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private List<SliderModel> mSliderModelList;

    public SliderAdapter(List<SliderModel> sliderModelList) {
        mSliderModelList = sliderModelList;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slider_layout,container,false);
        ConstraintLayout bannerContainer = view.findViewById(R.id.banner_container);
        bannerContainer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(mSliderModelList.get(position).getBackgound_color())));
        ImageView banner = view.findViewById(R.id.banner_slide);
        //banner.setImageResource(mSliderModelList.get(position).getBanner());
        Glide.with(container.getContext()).load(mSliderModelList.get(position).getBanner()).apply(new RequestOptions().placeholder(R.mipmap.baseline_home_black_18dp)).into(banner);
       // Picasso.get(container.getContext()).load(mSliderModelList.get(position).getBanner())
               // .placeholder(R.mipmap.baseline_home_black_18dp).into(banner);
       // Picasso.get().load(mSliderModelList.get(position).getBanner()).error(R.mipmap.baseline_home_black_18dp).into(banner);
        container.addView(view,0);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return mSliderModelList.size();
    }
}
