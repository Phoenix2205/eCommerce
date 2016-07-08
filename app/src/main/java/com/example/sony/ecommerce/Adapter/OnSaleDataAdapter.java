package com.example.sony.ecommerce.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sony.ecommerce.Model.Product;
import com.example.sony.ecommerce.ProductDetailActivity;
import com.example.sony.ecommerce.R;

import java.util.List;

/**
 * Created by SONY on 6/13/2016.
 */
public class OnSaleDataAdapter extends RecyclerView.Adapter {
    Context context;
    List<Product> productsList;

    public OnSaleDataAdapter(Context context, List<Product> productsList) {
        this.context = context;
        this.productsList = productsList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragement_on_sale_recycler_view_cell,parent,false);
        ViewHolder vh= new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int  position) {
        Glide.with(context).load(productsList.get(position).getFeaturedSrc()).into(((ViewHolder) holder).productPhoto);
        ((ViewHolder) holder).productName.setText(productsList.get(position).getTitle());
        ((ViewHolder) holder).price.setText(productsList.get(position).getSalePrice());
        ((ViewHolder) holder).container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("ID",productsList.get(position).getId());
                intent.putExtra("Price",productsList.get(position).getSalePrice());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public static ImageView productPhoto;
        public static TextView productName;
        public static TextView price;
        public static CardView container;
        // public static Button add;

        public ViewHolder(View itemView) {
            super(itemView);
            productPhoto = (ImageView) itemView.findViewById(R.id.image_view_product_on_sale_cell);
            Log.d("productPhotoID",String.valueOf(R.id.image_view_product_on_sale_cell));
            productName = (TextView) itemView.findViewById(R.id.text_view_product_name_on_sale_cell);
            price = (TextView) itemView.findViewById(R.id.text_view_price_on_sale_cell);
            container=(CardView)itemView.findViewById(R.id.card_view_container_on_sale_cell);
            // add=(Button)itemView.findViewById(R.id.button_add_cell);

        }
    }
}

