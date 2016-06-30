package com.example.sony.ecommerce.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sony.ecommerce.Fragment.FragmentExploreChild;
import com.example.sony.ecommerce.Model.ProductCategory;

import java.util.List;

/**
 * Created by SONY on 6/11/2016.
 */
public class ViewPagerExploreAdapter  extends FragmentPagerAdapter {
    private Context context;
    List<ProductCategory> productCategoriesChild;
    public ViewPagerExploreAdapter(FragmentManager fm,Context context,List<ProductCategory>childList) {
        super(fm);
        this.context=context;
        this.productCategoriesChild=childList;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return FragmentExploreChild.newInstance(position ,context);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return productCategoriesChild.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //switch (position) {
//            case 0:
//                return "SECTION 1";
//            case 1:
//                return "SECTION 2";
//            case 2:
//                return "SECTION 3";
            return productCategoriesChild.get(position).getName();
        //}
      //  return null;
    }
}

