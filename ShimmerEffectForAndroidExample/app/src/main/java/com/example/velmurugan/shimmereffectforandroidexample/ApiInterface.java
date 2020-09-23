package com.example.velmurugan.shimmereffectforandroidexample;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
  @GET("shimmereffect.json")
  Call<List<Movie>> getAllMovies();
}
