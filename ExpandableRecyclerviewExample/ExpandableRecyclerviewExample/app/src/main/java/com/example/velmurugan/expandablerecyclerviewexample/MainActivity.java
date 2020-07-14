package com.example.velmurugan.expandablerecyclerviewexample;

import android.os.Bundle;

import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mindorks.placeholderview.ExpandablePlaceHolderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  private Map<String,List<Movie>> categoryMap;

  private List<Movie> movieList;

    private ExpandablePlaceHolderView expandablePlaceHolderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();
        categoryMap = new HashMap<>();
        expandablePlaceHolderView = (ExpandablePlaceHolderView) findViewById(R.id.expandablePlaceHolder);

        loadData();

        expandablePlaceHolderView.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(android.view.View view) {
            Toast.makeText(getApplicationContext(),"Clixcked", view.getId()).show();
          }
        });

    }

    private void loadData(){

        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        apiInterface.getAllMovies().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
              movieList = response.body();
              getHeaderAndChild(movieList);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

            }
        });

    }

    private void getHeaderAndChild(List<Movie> movieList){

      for (Movie movie : movieList ){
        List<Movie> movieList1 = categoryMap.get(movie.getCategoty());
        if(movieList1 == null){
          movieList1 = new ArrayList<>();
        }
        movieList1.add(movie);
        categoryMap.put(movie.getCategoty(),movieList1);
      }

      Log.d("Map",categoryMap.toString());
      Iterator it = categoryMap.entrySet().iterator();
      while (it.hasNext()) {
        Map.Entry pair = (Map.Entry)it.next();
        Log.d("Key", pair.getKey().toString());
        expandablePlaceHolderView.addView(new HeaderView(this, pair.getKey().toString()));
        List<Movie> movieList1 = (List<Movie>) pair.getValue();
        for (Movie movie : movieList1){
          expandablePlaceHolderView.addView(new ChildView(this, movie));
        }
        it.remove();
      }
    }


}
