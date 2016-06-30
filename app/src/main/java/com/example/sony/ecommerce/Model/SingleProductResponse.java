package com.example.sony.ecommerce.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SONY on 6/28/2016.
 */
public class SingleProductResponse {
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @SerializedName("product")
    @Expose
    private Product product = new Product();
}
