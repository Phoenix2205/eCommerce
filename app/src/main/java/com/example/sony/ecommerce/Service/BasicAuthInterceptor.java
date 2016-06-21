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
        String username="ck_7691c09948b410333973d5cd39db7423fed52030";
        String password="cs_8a8ae4cacae87eb4370585fd65da5f8f38587a88";
        String credentials =username+":"+password;
        final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        requestBuilder.addHeader("Authorization", basic);
        return chain.proceed(requestBuilder.build());
    }
}
