package com.example.farmtoflat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import android.widget.LinearLayout;

public class ViewAllActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private android.widget.GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Deals of the Day");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        int layout_code = getIntent().getIntExtra("layout_code", -1);

        recyclerView = findViewById(R.id.view_all_recycler_view);
        gridView = (android.widget.GridView) findViewById(R.id.view_all_grid_view);

         if (layout_code == 1) {
            ////2nd condition

            gridView.setVisibility(View.VISIBLE);

            List<HorizontalProductScrollModel_today> horizontalProductScrollModelList_todays = new ArrayList<>();

            horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg1, "Carrot", "10Kg", "Rs. 200/-"));
            horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg2_, "Potato", "10Kg", "Rs. 100/-"));
            horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg3, "Ladyfinger", "10Kg", "Rs. 300/-"));
            horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg4, "Tomato", "10Kg", "Rs. 200/-"));
            horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg5, "Shimla Mirch", "10Kg", "Rs. 500/-"));
            horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg6, "Chilli", "10Kg", "Rs. 400/-"));
            horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg1, "Carrot", "10Kg", "Rs. 200/-"));
            horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg2_, "Potato", "10Kg", "Rs. 100/-"));
            horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg3, "Ladyfinger", "10Kg", "Rs. 300/-"));
            horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg4, "Tomato", "10Kg", "Rs. 200/-"));
            horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg5, "Shimla Mirch", "10Kg", "Rs. 500/-"));
            horizontalProductScrollModelList_todays.add(new HorizontalProductScrollModel_today(R.drawable.veg6, "Chilli", "10Kg", "Rs. 400/-"));

            GridProductAdapter gridProductAdapter = new GridProductAdapter(horizontalProductScrollModelList_todays);
            gridView.setAdapter(gridProductAdapter);
        }
        if (layout_code == 0) {


            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);




            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            List<ViewAllModel> viewAllModelList = new ArrayList<>();
            viewAllModelList.add(new ViewAllModel(R.drawable.veg1, "Carrot", 1, "3", 150, "₹20/kg", "₹25/kg", "Cash on delivery"));
            viewAllModelList.add(new ViewAllModel(R.drawable.veg1, "Carrot", 0, "3", 150, "₹20/kg", "₹25/kg", "Cash on delivery"));
            viewAllModelList.add(new ViewAllModel(R.drawable.veg1, "Carrot", 5, "3", 150, "₹20/kg", "₹25/kg", "Cash on delivery"));
            viewAllModelList.add(new ViewAllModel(R.drawable.veg1, "Carrot", 3, "3", 150, "₹20/kg", "₹25/kg", "Cash on delivery"));
            viewAllModelList.add(new ViewAllModel(R.drawable.veg1, "Carrot", 2, "3", 150, "₹20/kg", "₹25/kg", "Cash on delivery"));
            viewAllModelList.add(new ViewAllModel(R.drawable.veg1, "Carrot", 1, "3", 150, "₹20/kg", "₹25/kg", "Cash on delivery"));
            viewAllModelList.add(new ViewAllModel(R.drawable.veg1, "Carrot", 1, "3", 150, "₹20/kg", "₹25/kg", "Cash on delivery"));
            viewAllModelList.add(new ViewAllModel(R.drawable.veg1, "Carrot", 1, "3", 150, "₹20/kg", "₹25/kg", "Cash on delivery"));
            viewAllModelList.add(new ViewAllModel(R.drawable.veg1, "Carrot", 1, "3", 150, "₹20/kg", "₹25/kg", "Cash on delivery"));
            viewAllModelList.add(new ViewAllModel(R.drawable.veg1, "Carrot", 1, "3", 150, "₹20/kg", "₹25/kg", "Cash on delivery"));

            ViewAllAdapter adapter = new ViewAllAdapter(viewAllModelList, false);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
                finish();
                return true;
            }
            return super.onOptionsItemSelected(item);


    }
}
