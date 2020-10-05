package com.example.nextcode.nextcodeApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final Retrofit retrofit =new Retrofit.Builder()
            .baseUrl("http://34.209.128.166:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
