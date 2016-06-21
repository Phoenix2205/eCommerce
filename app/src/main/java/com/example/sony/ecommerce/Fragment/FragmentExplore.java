package com.example.sony.ecommerce.Fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.sony.ecommerce.Adapter.SpinnerAdapter;
import com.example.sony.ecommerce.Adapter.ViewPagerExploreAdapter;
import com.example.sony.ecommerce.Model.CategoryResponse;
import com.example.sony.ecommerce.Model.ProductCategory;
import com.example.sony.ecommerce.R;
import com.example.sony.ecommerce.Service.ServiceGenerator;
import com.example.sony.ecommerce.Service.WooCommerceService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//CAI NAY DUNG DE INFLATE TAB VA VIEWPAFER
/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentExplore extends Fragment {
    Toolbar toolbar;
    ViewPagerExploreAdapter viewPagerExploreAdapter;
    ViewPager mViewPager;
    TabLayout tabLayout;
    Spinner spinner;
    List<ProductCategory> productCat= new ArrayList<>();
    List<ProductCategory>productCatParent;
    public FragmentExplore() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_explore, container, false);
        // Inflate the layout for this fragment

        //set toolbar for fragment
        toolbar=(Toolbar)view.findViewById(R.id.toolbar_explore);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        viewPagerExploreAdapter = new ViewPagerExploreAdapter(getChildFragmentManager(),getActivity());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(viewPagerExploreAdapter);

        tabLayout = (TabLayout)view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mViewPager);

        spinner= (Spinner)view.findViewById(R.id.spinner);

        getCategory();


        return view;
    }

    private void getCategory() {
        WooCommerceService service= ServiceGenerator.createService(WooCommerceService.class);
        Call<CategoryResponse>categoryResponsecall=service.getListCategory();
        categoryResponsecall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                CategoryResponse categoryResponse=response.body();
                Log.d("category list",String.valueOf(categoryResponse.getProductCategories().size()));
                productCat=categoryResponse.getProductCategories();
                getParentCategory(productCat);
                spinner.setAdapter(new SpinnerAdapter(getActivity(),productCatParent));


            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.i("Failure","Fail to get cat");
            }
        });

    }

    private void getParentCategory(List<ProductCategory> productCat) {
        for (int i =0;i<productCat.size();i++)
        {
            if (productCat.get(i).getParent()==0)
                productCatParent.add(productCat.get(i));

        }

        getChildren(productCat);

       // createTree(productCatParent);
    }

    private void getChildren(List<ProductCategory> productCat) {
        for (int i =0;i<productCatParent.size();i++)
        {
            List<ProductCategory>productChild= new ArrayList<>();
            for (int y=0;y<productCat.size();y++)
            {
                if (productCatParent.get(i).getId()==productCat.get(y).getParent())
                    productChild.add(productCat.get(i));
            }
            productCatParent.get(i).setChildrenCat(productChild);
        }
    }

//    private void createTree(List<ProductCategory> productCatParent) {
//        Tree<ProductCategory>tree= new Tree<>();
//        ProductCategory root = new ProductCategory();
//        Node<ProductCategory> rootNode  = new Node<>(root);
//        tree.setRootElement(rootNode);
//        for (ProductCategory productCatgory:productCatParent){//lay parent
//            Node<ProductCategory> nodeParent = new Node<>(productCatgory);
//            rootNode.addChild(nodeParent);
//        }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu,menu);
    }
}
