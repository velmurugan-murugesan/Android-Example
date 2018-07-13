package velmm.com.swipecardviewandroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

  @GET("5a8fefef3000004f00248c70")
  Call<List<Movie>> getAllMovies();
}
