package velmm.com.pagingnationinrecyclerview.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import velmm.com.pagingnationinrecyclerview.model.Movie;

public interface MovieService {
    @GET("volley_array.json")
    Call<List<Movie>> getMovies();
}
