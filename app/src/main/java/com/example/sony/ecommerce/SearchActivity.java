package com.example.sony.ecommerce;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.sony.ecommerce.Adapter.SearchActivityAdapter;
import com.example.sony.ecommerce.Model.Product;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    RecyclerView resultListView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    RecyclerView.Adapter adapter;
    List<Product> resultList;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        resultListView=(RecyclerView)findViewById(R.id.recycler_view_search_activity);
        toolbar=(Toolbar)findViewById(R.id.toolbar_search_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle("Result List");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mStaggeredLayoutManager=new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        resultListView.setLayoutManager(mStaggeredLayoutManager);
        resultList= getIntent().getParcelableArrayListExtra("ResultList");
        adapter= new SearchActivityAdapter(this,resultList);
        resultListView.setAdapter(adapter);

    }


}
