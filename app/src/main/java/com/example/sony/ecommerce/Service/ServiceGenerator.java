package com.example.sony.ecommerce.Service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SONY on 6/11/2016.
 */
public class ServiceGenerator {
    public static final String API_BASE_URL="http://lshxf6oyz-site.gtempurl.com/wp/wc-api/v3/";
    private static OkHttpClient.Builder httpClient= new OkHttpClient.Builder().addInterceptor(new BasicAuthInterceptor());;
    public static Retrofit.Builder builder= new Retrofit.Builder().
            baseUrl(API_BASE_URL).
            addConverterFactory(GsonConverterFactory.create());
    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

}
