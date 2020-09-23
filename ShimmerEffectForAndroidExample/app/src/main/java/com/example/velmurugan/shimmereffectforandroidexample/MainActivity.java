package com.example.velmurugan.shimmereffectforandroidexample;

import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import com.facebook.shimmer.ShimmerFrameLayout;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  private ShimmerFrameLayout shimmerFrameLayout;
  private RecyclerView recyclerView;
  private RecyclerviewAdapter recyclerviewAdapter;
  private List<Movie> movieList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    shimmerFrameLayout = findViewById(R.id.shimmerLayout);
    shimmerFrameLayout.startShimmer();

    movieList = new ArrayList<>();
    recyclerviewAdapter = new RecyclerviewAdapter(this);
    recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(recyclerviewAdapter);

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        loadData();
      }
    }, 10000);
  }

  private void loadData(){
    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    apiInterface.getAllMovies().enqueue(new Callback<List<Movie>>() {
      @Override
      public void onResponse(retrofit2.Call<List<Movie>> call, Response<List<Movie>> response) {

        movieList = response.body();
        recyclerviewAdapter.setMovieList(movieList);
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
      }

      @Override
      public void onFailure(retrofit2.Call<List<Movie>> call, Throwable t) {

      }
    });
  }
}
