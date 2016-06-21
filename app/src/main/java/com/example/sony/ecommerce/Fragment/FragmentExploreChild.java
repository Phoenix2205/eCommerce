package com.example.sony.ecommerce.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sony.ecommerce.Adapter.ProductDataAdapter;
import com.example.sony.ecommerce.Model.Product;
import com.example.sony.ecommerce.Model.ProductResponse;
import com.example.sony.ecommerce.R;
import com.example.sony.ecommerce.Service.ServiceGenerator;
import com.example.sony.ecommerce.Service.WooCommerceService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentExploreChild extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    RecyclerView categoryListView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    static Context context;
    public FragmentExploreChild() {
        // Required empty public constructor
    }

    public static FragmentExploreChild newInstance(int sectionNumber,Context activityContext) {
        FragmentExploreChild fragment = new FragmentExploreChild();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        context=activityContext;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_explore_child, container, false);
        categoryListView=(RecyclerView)view.findViewById(R.id.recycler_view_explore_child);

        layoutManager =  new LinearLayoutManager(context);

        WooCommerceService service = ServiceGenerator.createService(WooCommerceService.class);
        Call<ProductResponse> ListCategoryResponseCall= service.getListProducts();
        ListCategoryResponseCall.enqueue(new Callback<ProductResponse>() {

            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                ProductResponse productResponse=response.body();
                Log.d("Product List Size",String.valueOf(productResponse.getProducts().size()));
                List<Product>productList=productResponse.getProducts();
                categoryListView.setLayoutManager(layoutManager);
                adapter = new ProductDataAdapter(context,productList);
                categoryListView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });


        return view;
    }



}
