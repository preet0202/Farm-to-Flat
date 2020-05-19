package com.example.farmtoflat;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter mCategoryAdapter;

    /// banner slider
    private ViewPager bannerSliderViewPager;
    private List<SliderModel> mSliderModelList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    /// banner slider

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager  layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();

        categoryModelList.add(new CategoryModel("link","Home"));
        categoryModelList.add(new CategoryModel("link","Price Comparision"));
        categoryModelList.add(new CategoryModel("link","Offer"));
        categoryModelList.add(new CategoryModel("link","Order Now"));
        categoryModelList.add(new CategoryModel("link","Subscription"));
        categoryModelList.add(new CategoryModel("link","More"));

        mCategoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(mCategoryAdapter);
        mCategoryAdapter.notifyDataSetChanged();

        ///// Banner Slider

        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);
        mSliderModelList = new ArrayList<SliderModel>();

        mSliderModelList.add(new SliderModel(R.drawable.photo5,"#077AE4"));
        mSliderModelList.add(new SliderModel(R.drawable.photo6,"#077AE4"));

        mSliderModelList.add(new SliderModel(R.drawable.photo1,"#077AE4"));
        mSliderModelList.add(new SliderModel(R.drawable.photo2,"#077AE4"));
        mSliderModelList.add(new SliderModel(R.drawable.photo3,"#077AE4"));
        mSliderModelList.add(new SliderModel(R.drawable.photo4,"#077AE4"));
        mSliderModelList.add(new SliderModel(R.drawable.photo5,"#077AE4"));
        mSliderModelList.add(new SliderModel(R.drawable.photo6,"#077AE4"));

        mSliderModelList.add(new SliderModel(R.drawable.photo1,"#077AE4"));
        mSliderModelList.add(new SliderModel(R.drawable.photo2,"#077AE4"));


        SliderAdapter  sliderAdapter= new SliderAdapter(mSliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);

        bannerSliderViewPager.setCurrentItem(currentPage);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                    if(state == ViewPager.SCROLL_STATE_IDLE){
                        pageLooper();
                    }
            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startbannerSlideShow();
        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View view, MotionEvent motionEvent) {
                 pageLooper();
                 stopbannerSlideShow();
                 if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                     startbannerSlideShow();
                 }
                 return false;
             }
         });
        //// Banner Slider

        return view;

    }

    //// Banner Slider

    private void pageLooper(){
        if(currentPage == mSliderModelList.size()-2){
            currentPage=2;
            bannerSliderViewPager.setCurrentItem(currentPage,false);
        }
        if(currentPage == 1){
            currentPage=mSliderModelList.size()-3;
            bannerSliderViewPager.setCurrentItem(currentPage,false);
        }
    }

    private void startbannerSlideShow() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable(){
            @Override
            public void run() {
                if(currentPage >= mSliderModelList.size()){
                    currentPage =1;
                }
                bannerSliderViewPager.setCurrentItem(currentPage++,true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME,PERIOD_TIME);
    }
    private void stopbannerSlideShow() {
        timer.cancel();
    }

    ///banner Slider
}
