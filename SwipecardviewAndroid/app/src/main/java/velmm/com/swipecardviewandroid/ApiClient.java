package velmm.com.swipecardviewandroid;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

  private static String BASE_URL="http://www.mocky.io/v2/";

  private static Retrofit retrofit;

  public static Retrofit getInstance(){
    if(retrofit == null){
      retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }
    return retrofit;
  }
}
