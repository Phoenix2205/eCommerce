package com.example.sony.ecommerce.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sony.ecommerce.Adapter.ProductDataAdapter;
import com.example.sony.ecommerce.Adapter.SpacesItemDecoration;
import com.example.sony.ecommerce.Model.Product;
import com.example.sony.ecommerce.Model.ProductResponse;
import com.example.sony.ecommerce.R;
import com.example.sony.ecommerce.Service.ServiceGenerator;
import com.example.sony.ecommerce.Service.WooCommerceService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOnSale extends Fragment {

    ImageView imageViewProduct;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<Product> productsOnSale;

    public FragmentOnSale() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_on_sale,container,false);
        imageViewProduct=(ImageView)v.findViewById(R.id.image_view_banner);
        Glide.with(getActivity()).load(R.drawable.promo_banner).into(imageViewProduct);


        recyclerView=(RecyclerView)v.findViewById(R.id.recycler_view_on_sale);

        WooCommerceService wooCommerceService= ServiceGenerator.createService(WooCommerceService.class);
        Call<ProductResponse> ListCategoryResponseCall=wooCommerceService.getListProducts();

        ListCategoryResponseCall.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                ProductResponse productResponse=response.body();
                Log.d("Product List Size",String.valueOf(productResponse.getProducts().size()));
                List<Product> productList=productResponse.getProducts();


                productsOnSale=getProductsOnSale(productList);
                Log.d("Product On Sale",String.valueOf(productsOnSale.size()));
                adapter = new ProductDataAdapter(getActivity(),productsOnSale);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                SpacesItemDecoration decoration = new SpacesItemDecoration(16);
                recyclerView.addItemDecoration(decoration);

            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });


        return v;
    }

    private List<Product> getProductsOnSale (List<Product>productList)
    {
        List<Product>temp=new ArrayList<>();
        for (int i =0;i<productList.size();i++)
            if(productList.get(i).getOnSale()) temp.add(productList.get(i));
        return temp;
    }


}
