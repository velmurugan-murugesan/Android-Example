package velmm.com.glideandroid;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;

import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.*;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageview);

        /*//Normal way
        Glide.with(this)
                .load("imageUrl")
                .into(imageView);


        //With PlaceHolder
        Glide.with(this)
                .load("imageUrl")
                .apply(RequestOptions.placeholderOf(R.drawable.no_image))
                .into(imageView);*/


        //With Placeholder and error placeholder
        /*Glide.with(this)
                .load("imageUrl")
                .apply(RequestOptions.placeholderOf(R.drawable.no_image))
                .apply(RequestOptions.errorOf(R.drawable.error))
                .into(imageView);
*/

        //Resizing image
        /*Glide.with(this)
                .load("imageUrl")
                .apply(RequestOptions.placeholderOf(R.drawable.no_image))
                .apply(RequestOptions.overrideOf(300,300))
                .into(imageView);*/

        /*Glide.with(this)
                .load("imageUrl")
                .apply(RequestOptions.overrideOf(100,100))
                .apply(RequestOptions.centerCropTransform())
                .into(imageView);*/

        /*Glide.with(this)
                .load("imageUrl")
                .apply(RequestOptions.overrideOf(100,100))
                .apply(RequestOptions.fitCenterTransform())
                .into(imageView);*/


        //Croping image
        //center crop
        /*Glide.with(this)
                .load("imageUrl")
                .apply(RequestOptions.centerCropTransform())
                .into(imageView);*/
        //Circle crop
        Glide.with(this)
                .load("imageUrl")
                .apply(RequestOptions.circleCropTransform())
                .into(imageView);

        /*Glide.with(this)
                .load("imageUrl")
                .apply(RequestOptions.fitCenterTransform())
                .into(imageView);*/

        Glide.with(this)
                .load("imageUrl")
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
        .into(imageView);


    }
}
