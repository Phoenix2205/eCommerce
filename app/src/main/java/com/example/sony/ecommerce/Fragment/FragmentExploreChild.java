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
import android.widget.Toast;

import com.example.sony.ecommerce.Adapter.ProductDataAdapter;
import com.example.sony.ecommerce.Message.MessageEvent;
import com.example.sony.ecommerce.Message.SearchKey;
import com.example.sony.ecommerce.Model.Product;
import com.example.sony.ecommerce.Model.ProductResponse;
import com.example.sony.ecommerce.R;
import com.example.sony.ecommerce.Service.ServiceGenerator;
import com.example.sony.ecommerce.Service.WooCommerceService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
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
    List<Product>productList;
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
       // EventBus.getDefault().register(context);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_explore_child, container, false);
        categoryListView=(RecyclerView)view.findViewById(R.id.recycler_view_explore_child);
        layoutManager =  new LinearLayoutManager(context);


        return view;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event){
        Toast.makeText(getActivity(), event.message, Toast.LENGTH_SHORT).show();
       String catName=event.message;
        getData(catName);
        Log.i("Tab name received",catName);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetKeyWord(SearchKey event){
        Toast.makeText(getActivity(), event.message, Toast.LENGTH_SHORT).show();
        getResultSearch(event.message);

    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    void getData( String catName)
    {
        WooCommerceService service = ServiceGenerator.createService(WooCommerceService.class);
        Call<ProductResponse> ListCategoryResponseCall = service.getListProductByCatName(catName);
        ListCategoryResponseCall.enqueue(new Callback<ProductResponse>() {

            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                ProductResponse productResponse=response.body();
                Log.d("Product List Size",String.valueOf(productResponse.getProducts().size()));
                productList=productResponse.getProducts();
                categoryListView.setLayoutManager(layoutManager);
                adapter = new ProductDataAdapter(context,productList);
                categoryListView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });
    }

    void getResultSearch(String keyword)
    {
       for (int i =0;i<productList.size();i++) {
           List<Product>TempList= new ArrayList<>();
           if (productList.get(i).getTitle().equals(keyword))
               TempList.add(productList.get(i));
           categoryListView.setAdapter(new ProductDataAdapter(context,TempList));
       }

    }

}
