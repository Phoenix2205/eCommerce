package com.example.sony.ecommerce.Model;

import java.util.List;

/**
 * Created by SONY on 6/11/2016.
 */
public class GlobalApplication {

    public static List<Product> getProductLists() {
        return ProductLists;
    }

    public static void setProductLists(List<Product> productLists) {
        ProductLists = productLists;
    }

    public static List<Product>ProductLists;


}
