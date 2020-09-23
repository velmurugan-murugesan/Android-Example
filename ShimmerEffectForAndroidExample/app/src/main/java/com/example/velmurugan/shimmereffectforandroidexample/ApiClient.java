package com.example.velmurugan.shimmereffectforandroidexample;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

  private static Retrofit retrofit;
  private static String BASE_URL = "http://velmm.com/apis/";

  public static Retrofit getClient(){

    if(retrofit == null){
      retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create()).build();
    }
    return retrofit;
  }

}
