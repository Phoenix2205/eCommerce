package com.example.sony.ecommerce.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
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
 * Created by SONY on 7/8/2016.
 */
public class CartDataAdapter extends RecyclerView.Adapter {
    Context context;
    List<Product> productList;

    public CartDataAdapter(Context context, List<Product> products) {
        this.context = context;
        this.productList = products;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_cart_cell,parent,false);
        ViewHolder vh= new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int  position) {
        Glide.with(context).load(productList.get(position).getFeaturedSrc()).into(((ViewHolder) holder).productPhoto);
        ((ViewHolder) holder).productName.setText((productList.get(position).getTitle()));
        ((ViewHolder) holder).price.setText((productList.get(position).getPrice()+ "$ "));
        ((ViewHolder) holder).quantity.setText("Quantity: "+(String.valueOf(productList.get(position).getQuantity())));
//        ((ViewHolder)holder).cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(context, ProductDetailActivity.class);
//                intent.putExtra("ID",(productList.get(position).getId()));
//                intent.putExtra("NormalPrice", (productList.get(position).getPrice()+ "$ " ));
//                intent.putExtra("Photo",(productList.get(position).getFeaturedSrc()));
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public static ImageView productPhoto;
        public static TextView productName;
        public static TextView price;
        public static Button remove;
        public static CardView cardView;
        public static TextView quantity;

        public ViewHolder(View itemView) {
            super(itemView);
            productPhoto = (ImageView) itemView.findViewById(R.id.image_view_product_cart_cell);
            productName = (TextView) itemView.findViewById(R.id.text_view_product_name_cart_cell);
            price = (TextView) itemView.findViewById(R.id.text_view_price_cart_cell);
            remove = (Button) itemView.findViewById(R.id.button_remove_cart_cell);
            cardView = (CardView) itemView.findViewById(R.id.card_view_product_cart_cell);
            quantity=(TextView)itemView.findViewById(R.id.text_view_quantity_cart_cell);
        }
    }
}