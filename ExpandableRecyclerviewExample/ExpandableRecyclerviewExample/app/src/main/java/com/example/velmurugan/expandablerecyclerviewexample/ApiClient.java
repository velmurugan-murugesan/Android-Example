package com.example.velmurugan.expandablerecyclerviewexample;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

  private static Retrofit retrofit;
  private static String BASE_URL="https://howtodoandroid.com/";

  public static Retrofit getInstance(){
    if(retrofit == null){
      retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }
    return retrofit;
  }
}
