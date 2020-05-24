package com.example.farmtoflat;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {

    private List<HomePageModel> mHomePageModelList;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        mHomePageModelList = homePageModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (mHomePageModelList.get(position).getType()) {
            case 0:
                return HomePageModel.BANNER_SLIDER;
            case 1:
                return HomePageModel.SPECIAL_OFFER_SLIDER;
            case 2:
                return HomePageModel.HORIZONTAL_PRODUCT_VIEW;
            case 3:
                return HomePageModel.GRID_PRODUCT_VIEW;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case HomePageModel.BANNER_SLIDER:
                View bannerSliderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliding_ad_layout,parent,false);
                return new BannerSliderViewHolder(bannerSliderView);

            case HomePageModel.SPECIAL_OFFER_SLIDER:
                View specialOfferSliderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.special_offer_layout,parent,false);
                return new SpecialOfferSliderViewHolder(specialOfferSliderView);

            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                View horizintalProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_layout1,parent,false);
                return new HorizontalProductViewHolder(horizintalProductView);

            case HomePageModel.GRID_PRODUCT_VIEW:
                View gridProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_product_layout,parent,false);
                return new GridProductViewHolder(gridProductView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (mHomePageModelList.get(position).getType()) {
            case HomePageModel.BANNER_SLIDER:
                List<SliderModel> sliderModelList = mHomePageModelList.get(position).getSliderModelList();
                ((BannerSliderViewHolder)holder).setBannerSliderViewPager(sliderModelList);
                break;

            case HomePageModel.SPECIAL_OFFER_SLIDER:
                List<SliderModel> specialOfferModelList = mHomePageModelList.get(position).getSliderModelList();
                ((SpecialOfferSliderViewHolder)holder).setSpecialOfferSliderViewPager(specialOfferModelList);
                break;

            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                String horizontallayouttitle = mHomePageModelList.get(position).getTitle();
                List<HorizontalProductScrollModel_today> horizontalProductScrollModel_todayList = mHomePageModelList.get(position).getHorizontalProductScrollModel_todayList();
                ((HorizontalProductViewHolder)holder).setHorizontalProductLayout(horizontalProductScrollModel_todayList,horizontallayouttitle);
                break;

            case HomePageModel.GRID_PRODUCT_VIEW:
                String gridlayouttitle = mHomePageModelList.get(position).getTitle();
                List<HorizontalProductScrollModel_today> gridProductScrollModel_todayList = mHomePageModelList.get(position).getHorizontalProductScrollModel_todayList();
                ((GridProductViewHolder)holder).setGridProductLayout(gridProductScrollModel_todayList,gridlayouttitle);
                break;

            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return mHomePageModelList.size();
    }

    public class BannerSliderViewHolder extends RecyclerView.ViewHolder {

        private ViewPager bannerSliderViewPager;
        private int currentPage = 2;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;

        public BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerSliderViewPager = itemView.findViewById(R.id.banner_slider_view_pager);
        }
        private void setBannerSliderViewPager(final List<SliderModel> mSliderModelList){
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
                        pageLooper(mSliderModelList);
                    }
                }
            };
            bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

            startbannerSlideShow(mSliderModelList);
            bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    pageLooper(mSliderModelList);
                    stopbannerSlideShow();
                    if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                        startbannerSlideShow(mSliderModelList);
                    }
                    return false;
                }
            });
        }
        private void pageLooper(List<SliderModel> mSliderModelList){
            if(currentPage == mSliderModelList.size()-2){
                currentPage=2;
                bannerSliderViewPager.setCurrentItem(currentPage,false);
            }
            if(currentPage == 1){
                currentPage=mSliderModelList.size()-3;
                bannerSliderViewPager.setCurrentItem(currentPage,false);
            }
        }
        private void startbannerSlideShow(final List<SliderModel> mSliderModelList) {
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
    }
    public class SpecialOfferSliderViewHolder extends RecyclerView.ViewHolder{

        private ViewPager specialofferViewPager;
        private int currentPage = 2;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;

        public SpecialOfferSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            specialofferViewPager = itemView.findViewById(R.id.special_offer_viewpager);
        }
        private void setSpecialOfferSliderViewPager(final List<SliderModel> mSliderModelList){

            SpecialOfferSliderAdapter  specialOfferSliderAdapter= new SpecialOfferSliderAdapter(mSliderModelList);
            specialofferViewPager.setAdapter(specialOfferSliderAdapter);
            specialofferViewPager.setClipToPadding(false);
            specialofferViewPager.setPageMargin(20);

            specialofferViewPager.setCurrentItem(currentPage);

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
                        pageLooper(mSliderModelList);
                    }
                }
            };
            specialofferViewPager.addOnPageChangeListener(onPageChangeListener);

            startbannerSlideShow(mSliderModelList);
            specialofferViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    pageLooper(mSliderModelList);
                    stopbannerSlideShow();
                    if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                        startbannerSlideShow(mSliderModelList);
                    }
                    return false;
                }
            });
        }
        private void pageLooper(List<SliderModel> mSliderModelList){
            if(currentPage == mSliderModelList.size()-2){
                currentPage=2;
                specialofferViewPager.setCurrentItem(currentPage,false);
            }
            if(currentPage == 1){
                currentPage=mSliderModelList.size()-3;
                specialofferViewPager.setCurrentItem(currentPage,false);
            }
        }
        private void startbannerSlideShow(final List<SliderModel> mSliderModelList) {
            final Handler handler = new Handler();
            final Runnable update = new Runnable(){
                @Override
                public void run() {
                    if(currentPage >= mSliderModelList.size()){
                        currentPage =1;
                    }
                    specialofferViewPager.setCurrentItem(currentPage++,true);
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
    }
    public class HorizontalProductViewHolder extends  RecyclerView.ViewHolder {

        private TextView horizontalLayoutTitle1;
        private Button horizontalviewAllButton1;
        private RecyclerView horizontalRecyclerView1;

        public HorizontalProductViewHolder(@NonNull View itemView) {
            super(itemView);
            horizontalLayoutTitle1 = itemView.findViewById(R.id.horizontal_scroll_layout1_title);
            horizontalviewAllButton1 =itemView.findViewById(R.id.horizontal_scroll_layout1_view_all_button);
            horizontalRecyclerView1 = itemView.findViewById(R.id.horizontal_scroll_layout1_recylerview);
        }
        private void setHorizontalProductLayout(List<HorizontalProductScrollModel_today> horizontalProductScrollModelList_todays, String title){

            horizontalLayoutTitle1.setText(title);
            if(horizontalProductScrollModelList_todays.size()>4){
                horizontalviewAllButton1.setVisibility(View.VISIBLE);
            }
            else{
                horizontalviewAllButton1.setVisibility(View.INVISIBLE);
            }
            HorizontalProductScrollAdapter_today horizontalProductScrollAdapter_today = new HorizontalProductScrollAdapter_today(horizontalProductScrollModelList_todays);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            horizontalRecyclerView1.setLayoutManager(linearLayoutManager);

            horizontalRecyclerView1.setAdapter(horizontalProductScrollAdapter_today);
            horizontalProductScrollAdapter_today.notifyDataSetChanged();
        }
    }

    public class GridProductViewHolder extends RecyclerView.ViewHolder{

        private TextView gridLayoutTitle;
        private Button gridviewAllButton;
        private GridView gridView;

        public GridProductViewHolder(@NonNull View itemView) {
            super(itemView);
            gridLayoutTitle = itemView.findViewById(R.id.grid_product_layout_title);
            gridviewAllButton =itemView.findViewById(R.id.grid_product_layout_viewall);
            gridView = itemView.findViewById(R.id.grid_product_layout_gridview);
        }
        private void setGridProductLayout(List<HorizontalProductScrollModel_today> horizontalProductScrollModelList_todays,String title){
            gridLayoutTitle.setText(title);
            gridView.setAdapter(new GridProductAdapter(horizontalProductScrollModelList_todays));
        }
    }

}
