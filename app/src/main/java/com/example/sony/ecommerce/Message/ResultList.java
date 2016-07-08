package com.example.sony.ecommerce.Message;

import com.example.sony.ecommerce.Model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SONY on 7/6/2016.
 */
public class ResultList {
    public List<Product> getResultList() {
        return resultList;
    }

    public void setResultList(List<Product> resultList) {
        this.resultList = resultList;
    }

    ResultList(List<Product> resultList)
    {
        this.resultList=resultList;
    }
    List<Product> resultList = new ArrayList<>();
}
