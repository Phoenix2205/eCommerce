package com.example.sony.ecommerce.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sony.ecommerce.Model.Product;
import com.example.sony.ecommerce.R;

import java.util.List;

/**
 * Created by SONY on 6/11/2016.
 */
public class ProductDataAdapter extends RecyclerView.Adapter {
    List<Product> mDataSet;
    Context context;
    public ProductDataAdapter(Context context, List<Product> data ) {
        this.context=context;
        this.mDataSet=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_child_recycler_view_cell,parent,false);
        ViewHolder vh= new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(context).load(mDataSet.get(position).getFeaturedSrc()).into(((ViewHolder) holder).productPhoto);
        ((ViewHolder) holder).productName.setText(mDataSet.get(position).getTitle());
        ((ViewHolder) holder).price.setText(mDataSet.get(position).getPrice()+ "$ ");

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public static ImageView productPhoto;
       // public static TextView categoryName;
        public static TextView productName;
        public static TextView price;
        public static Button add;

        public ViewHolder(View itemView) {
            super(itemView);
            productPhoto=(ImageView)itemView.findViewById(R.id.image_view_product_explore_cell);
         //   categoryName=(TextView) itemView.findViewById(R.id.);
            productName=(TextView)itemView.findViewById(R.id.text_view_product_name_explore_cell);
            price=(TextView)itemView.findViewById(R.id.text_view_price_explore_cell);
            add=(Button)itemView.findViewById(R.id.button_add_explore_cell);

        }
    }
}
