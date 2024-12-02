package com.example.choppingblock_home;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl) //api base url
                    .addConverterFactory(GsonConverterFactory.create()) //Gson converter
                    .build();
        }
        return retrofit;
    }
}
