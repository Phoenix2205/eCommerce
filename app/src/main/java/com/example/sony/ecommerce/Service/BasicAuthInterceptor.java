package com.example.sony.ecommerce.Service;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SONY on 6/11/2016.
 */
public class BasicAuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder=chain.request().newBuilder();
        String username="ck_f0e855a1f262c1f9b467db40da3fa4e6574422d1";
        String password="cs_64d8bcfdd36f8cedd73fefa7455bba645cc4cd4a";
        String credentials =username+":"+password;
        final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        requestBuilder.addHeader("Authorization", basic);
        return chain.proceed(requestBuilder.build());
    }
}
