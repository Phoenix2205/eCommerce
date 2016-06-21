package com.example.sony.ecommerce.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sony.ecommerce.R;

/**
 * Created by SONY on 6/11/2016.
 */
public class ImageSliderAdapter extends PagerAdapter {
    Context context;

    int[] imageList;

    public ImageSliderAdapter(Context context, int[] imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_pager_image, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Glide.with(context).load(imageList[position]).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        (container).removeView((View) object);
    }
}

