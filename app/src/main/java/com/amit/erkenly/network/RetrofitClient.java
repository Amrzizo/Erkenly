package com.amit.erkenly.network;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofitInstance;

    private static String baseURL = "http://erkenly.000webhostapp.com/api/";

    public static Retrofit getRetrofitInstance() {

        if(retrofitInstance == null){
            retrofitInstance = new retrofit2.Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            }

        return retrofitInstance;
        }
}
