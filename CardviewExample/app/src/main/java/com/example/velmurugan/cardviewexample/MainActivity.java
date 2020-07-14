package com.example.velmurugan.cardviewexample;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Movie> movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();
        prepareMovie();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(movieList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        recyclerViewAdapter.setOnItemClickListener(new ClickListener<Movie>(){
            @Override
            public void onItemClick(Movie data) {
                Toast.makeText(MainActivity.this, data.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(recyclerViewAdapter);

    }


    private void prepareMovie(){
        Movie movie = new Movie("Star Wars The Last Jedi",R.drawable.star_war);
        movieList.add(movie);
        movie = new Movie("Coco",R.drawable.coco);
        movieList.add(movie);
        movie = new Movie("Justice League ",R.drawable.justice_league);
        movieList.add(movie);
        movie = new Movie("Thor: Ragnarok",R.drawable.thor_ragnarok);
        movieList.add(movie);
        movie = new Movie("Star Wars The Last Jedi",R.drawable.star_war);
        movieList.add(movie);
        movie = new Movie("Coco",R.drawable.coco);
        movieList.add(movie);
        movie = new Movie("Justice League ",R.drawable.justice_league);
        movieList.add(movie);
        movie = new Movie("Thor: Ragnarok",R.drawable.thor_ragnarok);
        movieList.add(movie);
        movie = new Movie("Star Wars The Last Jedi",R.drawable.star_war);
        movieList.add(movie);
        movie = new Movie("Coco",R.drawable.coco);
        movieList.add(movie);
        movie = new Movie("Justice League ",R.drawable.justice_league);
        movieList.add(movie);
        movie = new Movie("Thor: Ragnarok",R.drawable.thor_ragnarok);
        movieList.add(movie);
        recyclerViewAdapter.notifyDataSetChanged();
    }

}
