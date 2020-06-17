package com.example.farmtoflat;

import android.content.Intent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {

    private List<HomePageModel> mHomePageModelList;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        mHomePageModelList = homePageModelList;
        recycledViewPool = new RecyclerView.RecycledViewPool();
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
            case 4:
                return HomePageModel.LUCKY_COUPON_VIEW;
            case 5:
                return HomePageModel.FOLLOW_US;
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

            case HomePageModel.LUCKY_COUPON_VIEW:
                View luckyCouponView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lucky_coupons,parent,false);
                return new LuckyCouponViewHolder(luckyCouponView);

            case HomePageModel.FOLLOW_US:
                View followus = LayoutInflater.from(parent.getContext()).inflate(R.layout.follow_and_feedback,parent,false);
                return new FolllowandFeedbackViewHolder(followus);
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

            case HomePageModel.LUCKY_COUPON_VIEW:
                String luckyCoupontitle = mHomePageModelList.get(position).getTitle();
                List<LuckyCouponModel> luckyCouponModelList = mHomePageModelList.get(position).getLuckyCouponModelList();
                ((LuckyCouponViewHolder)holder).setLuckyCouponLayout(luckyCoupontitle,luckyCouponModelList);
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
        private int currentPage ;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;
        private List<SliderModel> arrangedList;

        public BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerSliderViewPager = itemView.findViewById(R.id.banner_slider_view_pager);
        }
        private void setBannerSliderViewPager(final List<SliderModel> mSliderModelList){
            currentPage =2;
            if(timer != null){
                timer.cancel();
            }
            arrangedList = new ArrayList<>();
            for(int x=0;x < mSliderModelList.size();x++){
                arrangedList.add(x,mSliderModelList.get(x));
            }

            arrangedList.add(0,mSliderModelList.get(mSliderModelList.size()-2));
            arrangedList.add(1,mSliderModelList.get(mSliderModelList.size()-1));
            arrangedList.add(mSliderModelList.get(0));
            arrangedList.add(mSliderModelList.get(1));

            SliderAdapter  sliderAdapter= new SliderAdapter(arrangedList);
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
                        pageLooper(arrangedList);
                    }
                }
            };
            bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

            startbannerSlideShow(arrangedList);
            bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    pageLooper(arrangedList);
                    stopbannerSlideShow();
                    if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                        startbannerSlideShow(arrangedList);
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
        private int currentPage;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;
        private List<SliderModel> arrangedList;

        public SpecialOfferSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            specialofferViewPager = itemView.findViewById(R.id.special_offer_viewpager);
        }
        private void setSpecialOfferSliderViewPager(final List<SliderModel> mSliderModelList){

            currentPage =2;
            if(timer != null){
                timer.cancel();
            }
            arrangedList = new ArrayList<>();
            for(int x=0;x < mSliderModelList.size();x++){
                arrangedList.add(x,mSliderModelList.get(x));
            }

            arrangedList.add(0,mSliderModelList.get(mSliderModelList.size()-2));
            arrangedList.add(1,mSliderModelList.get(mSliderModelList.size()-1));
            arrangedList.add(mSliderModelList.get(0));
            arrangedList.add(mSliderModelList.get(1));

            SpecialOfferSliderAdapter  specialOfferSliderAdapter= new SpecialOfferSliderAdapter(arrangedList);
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
                        pageLooper(arrangedList);
                    }
                }
            };
            specialofferViewPager.addOnPageChangeListener(onPageChangeListener);

            startbannerSlideShow(arrangedList);
            specialofferViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    pageLooper(arrangedList);
                    stopbannerSlideShow();
                    if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                        startbannerSlideShow(arrangedList);
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
            horizontalRecyclerView1.setRecycledViewPool(recycledViewPool);
        }
        private void setHorizontalProductLayout(List<HorizontalProductScrollModel_today> horizontalProductScrollModelList_todays, String title){

            horizontalLayoutTitle1.setText(title);
            if(horizontalProductScrollModelList_todays.size()>4){
                horizontalviewAllButton1.setVisibility(View.VISIBLE);
                horizontalviewAllButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                        viewAllIntent.putExtra("layout_code",0);
                        itemView.getContext().startActivity(viewAllIntent);
                    }
                });
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
        private GridLayout gridProductLayout;

        public GridProductViewHolder(@NonNull View itemView) {
            super(itemView);
            gridLayoutTitle = itemView.findViewById(R.id.grid_product_layout_title);
            gridviewAllButton =itemView.findViewById(R.id.grid_product_layout_viewall);
            gridProductLayout = itemView.findViewById(R.id.grid_layout);

        }
        private void setGridProductLayout(List<HorizontalProductScrollModel_today> horizontalProductScrollModelList_todays,String title){
            gridLayoutTitle.setText(title);


            for (int x = 0;x < 4;x++) {

            }


            gridviewAllButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                    viewAllIntent.putExtra("layout_code",1);
                    itemView.getContext().startActivity(viewAllIntent);
                }
            });
        }
    }

    public class LuckyCouponViewHolder extends RecyclerView.ViewHolder{

        private TextView luckyCouponTitle;
        private ImageView photo1;
        private ImageView photo2;
        private ImageView photo3;
        public LuckyCouponViewHolder(@NonNull View itemView) {
            super(itemView);
            luckyCouponTitle = itemView.findViewById(R.id.lucky_coupon_title);
            photo1 = itemView.findViewById(R.id.lucky_coupon_image1);
            photo2 = itemView.findViewById(R.id.lucky_coupon_image2);
            photo3 = itemView.findViewById(R.id.lucky_coupon_image3);
        }
        private void setLuckyCouponLayout(String title,List<LuckyCouponModel> luckyCouponModelList){
            luckyCouponTitle.setText(title);
        }
    }

    public class FolllowandFeedbackViewHolder extends RecyclerView.ViewHolder{

        public FolllowandFeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
