package com.example.velmurugan.expandablerecyclerviewexample;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@Layout(R.layout.child_layout)
public class ChildView {
    private static String TAG ="ChildView";

    @View(R.id.child_name)
    TextView textViewChild;

    @View(R.id.child_desc)
    TextView textViewDesc;

    @View(R.id.child_image)
    ImageView childImage;

    private Context mContext;
    private Movie movie;

    public ChildView(Context mContext, Movie movie) {
        this.mContext = mContext;
        this.movie = movie;
    }

    @Resolve
    private void onResolve(){
        Log.d(TAG,"onResolve");
        textViewChild.setText(movie.getName());
        Glide.with(mContext).load(movie.getImageUrl()).apply(RequestOptions.circleCropTransform()).into(childImage);
    }
}
