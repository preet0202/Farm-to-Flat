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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter mCategoryAdapter;
    private   RecyclerView homePageRecyclerView;
    private List<CategoryModel> categoryModelList;
    private FirebaseFirestore firebaseFirestore;
    private HomePageAdapter adapter;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager  layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        categoryModelList = new ArrayList<CategoryModel>();

        mCategoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(mCategoryAdapter);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("categories").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                categoryModelList.add(new CategoryModel(documentSnapshot.get("icon").toString(),documentSnapshot.get("categoryName").toString()));
                            }
                        }else{
                            String error = task.getException().getMessage();
                            Toast.makeText(getContext() , error , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        mCategoryAdapter.notifyDataSetChanged();

        ///// Banner Slider
        //// Banner Slider

        /////Special offer pager

        List<SliderModel> mSpecialOfferModelList = new ArrayList<SliderModel>();
//

        /////Special offer pager

        ///Horizontal product 1
        List<HorizontalProductScrollModel_today> horizontalProductScrollModelList_todays = new ArrayList<>();

//        horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg1,"Carrot","10Kg","Rs. 200/-"));
//        horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg2_,"Potato","10Kg","Rs. 100/-"));
//        horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg3,"Ladyfinger","10Kg","Rs. 300/-"));
//        horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg4,"Tomato","10Kg","Rs. 200/-"));
//        horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg5,"Shimla Mirch","10Kg","Rs. 500/-"));
//        horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg6,"Chilli","10Kg","Rs. 400/-"));
        ///Horizontal product 1

        ////lucky Coupon

        List<LuckyCouponModel> luckyCouponModelList = new ArrayList<>();
      //  luckyCouponModelList.add(new LuckyCouponModel("Lucky Coupon"));
        ////lucky Coupon

        homePageRecyclerView  = view.findViewById(R.id.home_page_recyclerView);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homePageRecyclerView.setLayoutManager(testingLayoutManager);
        final List<HomePageModel> homePageModelList = new ArrayList<>();
        adapter = new HomePageAdapter(homePageModelList);
        homePageRecyclerView.setAdapter(adapter);

        firebaseFirestore.collection("top_deals").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){

                                if((long)documentSnapshot.get("view_type") == 0){
                                    List<SliderModel> sliderModelList = new ArrayList<SliderModel>();
                                    long  no_of_banners = (long)documentSnapshot.get("no_of_banners");
                                    for(long  x=1 ;x < no_of_banners+1 ; x++){
                                        sliderModelList.add(new SliderModel(documentSnapshot.get("banner_"+x).toString()
                                                ,documentSnapshot.get("banner_"+x+"_background").toString()));
                                    }
                                    //homePageModelList.add(new HomePageModel(0,sliderModelList));
                                }else if((long)documentSnapshot.get("view_type") == 1){

                                }else if((long)documentSnapshot.get("view_type") == 2){

                                }else if((long)documentSnapshot.get("view_type") == 3){

                                }

                            }
                            adapter.notifyDataSetChanged();;
                        }else{
                            String error = task.getException().getMessage();
                            Toast.makeText(getContext() , error , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //ViewCompat.setNestedScrollingEnabled(testing, false);
        //////////////////////////////////////////

        return view;

    }
}
