package com.example.farmtoflat;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

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
    private   RecyclerView testing;



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
        List <SliderModel> mSliderModelList = new ArrayList<SliderModel>();
        mSliderModelList.add(new SliderModel(R.drawable.photo1,"#077AE4"));
        mSliderModelList.add(new SliderModel(R.drawable.photo2,"#077AE4"));
        mSliderModelList.add(new SliderModel(R.drawable.photo3,"#077AE4"));
        mSliderModelList.add(new SliderModel(R.drawable.photo4,"#077AE4"));
        mSliderModelList.add(new SliderModel(R.drawable.photo5,"#077AE4"));
        mSliderModelList.add(new SliderModel(R.drawable.photo6,"#077AE4"));
        //// Banner Slider

        /////Special offer pager

        List<SliderModel> mSpecialOfferModelList = new ArrayList<SliderModel>();
        mSpecialOfferModelList.add(new SliderModel(R.drawable.specialoffer5,"#077AE4"));
        mSpecialOfferModelList.add(new SliderModel(R.drawable.specialoffer6,"#077AE4"));

        mSpecialOfferModelList.add(new SliderModel(R.drawable.specialoffer1,"#077AE4"));
        mSpecialOfferModelList.add(new SliderModel(R.drawable.specialoffer2,"#077AE4"));
        mSpecialOfferModelList.add(new SliderModel(R.drawable.specialoffer3,"#077AE4"));
        mSpecialOfferModelList.add(new SliderModel(R.drawable.specialoffer4,"#077AE4"));
        mSpecialOfferModelList.add(new SliderModel(R.drawable.specialoffer5,"#077AE4"));
        mSpecialOfferModelList.add(new SliderModel(R.drawable.specialoffer6,"#077AE4"));

        mSpecialOfferModelList.add(new SliderModel(R.drawable.specialoffer1,"#077AE4"));
        mSpecialOfferModelList.add(new SliderModel(R.drawable.specialoffer2,"#077AE4"));

        /////Special offer pager

        ///Horizontal product 1
        List<HorizontalProductScrollModel_today> horizontalProductScrollModelList_todays = new ArrayList<>();

        horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg1,"Carrot","10Kg","Rs. 200/-"));
        horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg2_,"Potato","10Kg","Rs. 100/-"));
        horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg3,"Ladyfinger","10Kg","Rs. 300/-"));
        horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg4,"Tomato","10Kg","Rs. 200/-"));
        horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg5,"Shimla Mirch","10Kg","Rs. 500/-"));
        horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg6,"Chilli","10Kg","Rs. 400/-"));
        ///Horizontal product 1

        ////lucky Coupon

        List<LuckyCouponModel> luckyCouponModelList = new ArrayList<>();
        luckyCouponModelList.add(new LuckyCouponModel("Lucky Coupon"));
        ////lucky Coupon

        ///////////////////////////////////////////
        testing  = view.findViewById(R.id.home_page_recyclerView);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0,mSliderModelList));
        homePageModelList.add(new HomePageModel(2,"Today's Deal",horizontalProductScrollModelList_todays));
        homePageModelList.add(new HomePageModel(3,"New Arrival",horizontalProductScrollModelList_todays));
//        homePageModelList.add(new HomePageModel(1,mSpecialOfferModelList));
        homePageModelList.add(new HomePageModel(4,luckyCouponModelList,"Lucky Coupon"));
        homePageModelList.add(new HomePageModel(5));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //ViewCompat.setNestedScrollingEnabled(testing, false);
        //////////////////////////////////////////

        return view;

    }
}
