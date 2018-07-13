package velmm.com.swipecardviewandroid;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipecardCallback{

    private Context context;
    private SwipePlaceHolderView swipePlaceHolderView;
    private Button buttonSelected, buttonRejected;
    private List<Movie> movieList;
    private int selected = 0,rejected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        movieList = new ArrayList<>();
        swipePlaceHolderView = findViewById(R.id.swipePlaceHolder);
        buttonSelected = findViewById(R.id.button_selected);
        buttonRejected = findViewById(R.id.button_rejected);

        swipePlaceHolderView.getBuilder().setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor().setPaddingTop(20)
                        .setRelativeScale(0.01f).setSwipeInMsgLayoutId(R.layout.swipe_in_layout)
                        .setSwipeOutMsgLayoutId(R.layout.swipe_out_layout));

        loadData();

        buttonSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipePlaceHolderView.doSwipe(true);
            }
        });

        buttonRejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipePlaceHolderView.doSwipe(false);
            }
        });

    }

    private void loadData() {

        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        apiInterface.getAllMovies().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                movieList = response.body();
                for (Movie movie : movieList){
                    swipePlaceHolderView.addView(new SwipecardView(context,swipePlaceHolderView,movie));
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    @Override
    public void onSwipeIn() {
        selected = selected + 1;
        buttonSelected.setText("Selected ("+selected+")");
    }

    @Override
    public void onSwipeOut() {
        rejected = rejected + 1;
        buttonRejected.setText("Rejected ("+rejected+")");
    }
}
