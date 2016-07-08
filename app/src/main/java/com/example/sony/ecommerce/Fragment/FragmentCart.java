package com.example.sony.ecommerce.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sony.ecommerce.Adapter.CartDataAdapter;
import com.example.sony.ecommerce.Database.DatabaseHelper;
import com.example.sony.ecommerce.Model.Product;
import com.example.sony.ecommerce.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCart extends Fragment {
    RecyclerView recyclerView;
    TextView totalPrice;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    RecyclerView.Adapter adapter;
    public FragmentCart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         DatabaseHelper databaseHelper = new DatabaseHelper(getActivity(),DatabaseHelper.DATABASE_NAME);
         View view= inflater.inflate(R.layout.fragment_cart, container, false);
         recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view_list_cart);
         mStaggeredLayoutManager =   new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
         recyclerView.setLayoutManager(mStaggeredLayoutManager);
         List<Product>productList=databaseHelper.GetDataTablebyUserName("abc");
         Log.d("List get by user name",String.valueOf(productList.size()));
         CartDataAdapter cartDataAdapter=new CartDataAdapter(getActivity(),productList);
         recyclerView.setAdapter(cartDataAdapter);
         totalPrice=(TextView)view.findViewById(R.id.text_view_total_cart);
         return  view;
    }

}
