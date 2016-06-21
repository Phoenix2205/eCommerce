package com.example.sony.ecommerce.Service;

import com.example.sony.ecommerce.Model.CategoryResponse;
import com.example.sony.ecommerce.Model.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by SONY on 6/11/2016.
 */
public interface WooCommerceService {

    @GET("products")
    Call<ProductResponse> getListProducts();

    @GET("products/{id}")
    Call<ProductResponse> getProductById(@Path("id") int id);


    @GET("products/categories")
    Call<CategoryResponse> getListCategory();
}
