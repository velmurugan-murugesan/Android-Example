package velmm.com.swipecardviewandroid;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;

@Layout(R.layout.swipecardview_layout)
public class SwipecardView {

    private SwipecardCallback swipecardCallback;

    @View(R.id.imageView)
    private ImageView imageView;
    @View(R.id.textview_name)
    private TextView textViewName;
    private Movie mMovie;
    private Context mContext;
    private SwipePlaceHolderView mSwipePlaceHolderView;
    public SwipecardView(Context context, SwipePlaceHolderView swipePlaceHolderView, Movie movie) {
        this.mContext = context;
        this.swipecardCallback = (SwipecardCallback) context;
        this.mSwipePlaceHolderView = swipePlaceHolderView;
        this.mMovie = movie;
    }

    @Resolve
    private void onResolve(){
        Glide.with(mContext).load(mMovie.getImageUrl()).apply(RequestOptions.centerCropTransform()).into(imageView);
        textViewName.setText(mMovie.getName());
    }

    @SwipeIn
    private void onSwipeIn(){
        swipecardCallback.onSwipeIn();
    }

    @SwipeOut
    private void onSwipeOut(){
        swipecardCallback.onSwipeOut();
    }

}
