package com.example.PCS_UAS_0609.httpclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitclient {
    public static final String BASE_URL="https://www.thesportsdb.com/api/v1/json/1/";
    public static Retrofit retrofit=null;

    public static Retrofit getRetrofitClient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
