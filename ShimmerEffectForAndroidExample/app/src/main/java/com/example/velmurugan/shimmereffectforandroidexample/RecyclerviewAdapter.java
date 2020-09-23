package com.example.velmurugan.shimmereffectforandroidexample;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder> {

  private List<Movie> movieList;

  private Context context;

  RecyclerviewAdapter(Context context){
    this.context = context;
  }

  public void setMovieList(List<Movie> movieList) {
    this.movieList = movieList;
    notifyDataSetChanged();
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_adapter,parent,false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, int position) {

    Movie movie = movieList.get(position);
      holder.textViewName.setText(movieList.get(position).getName());
      holder.textViewDesc.setText(movieList.get(position).getDesc());

      Glide.with(context).load(movie.getImageUrl()).into(holder.profileImage);
  }

  @Override
  public int getItemCount() {
    return movieList != null ? movieList.size() : 0;
  }

  public class MyViewHolder extends ViewHolder {

    private TextView textViewName;
    private TextView textViewDesc;
    private ShapeableImageView profileImage;

    public MyViewHolder(View itemView) {
      super(itemView);
      textViewName = (TextView) itemView.findViewById(R.id.name);
      textViewDesc = (TextView) itemView.findViewById(R.id.desc);
      profileImage = (ShapeableImageView) itemView.findViewById(R.id.profileImage);
    }
  }
}
