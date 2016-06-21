package com.example.sony.ecommerce.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sony.ecommerce.Model.ProductCategory;
import com.example.sony.ecommerce.R;

import java.util.List;

/**
 * Created by SONY on 6/21/2016.
 */
public class SpinnerAdapter extends ArrayAdapter<ProductCategory> {

    private Context context;
    // private ArrayList<String> data;
    private List<ProductCategory> categoryParent;
    public Resources res;
    LayoutInflater inflater;

  /* public SpinnerAdapter(Context context, ArrayList<String> objects) {
        super(context, R.layout.spinner_row, objects);

        context1 = context;
        data = objects;

        inflater = (LayoutInflater) context1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }*/

    public SpinnerAdapter(Context context, List<ProductCategory> objects) {
        super(context, R.layout.spinner_cell, objects);

        this.context = context;
        categoryParent = objects;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {

        View row = inflater.inflate(R.layout.spinner_cell, parent, false);

        TextView tvCategory = (TextView) row.findViewById(R.id.text_view_spinner_cell);

        // tvCategory.setText(data.get(position).toString());
        tvCategory.setText(categoryParent.get(position).getName());
        return row;
    }
}
