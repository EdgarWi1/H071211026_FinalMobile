package com.example.h071211026_finalmobile.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h071211026_finalmobile.DetailActivity;
import com.example.h071211026_finalmobile.R;
import com.example.h071211026_finalmobile.model.MovieResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private final ArrayList<MovieResponse> movieResponseList;

    public MovieAdapter(ArrayList<MovieResponse> movieResponseList){
        this.movieResponseList = movieResponseList;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MovieAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {

        MovieResponse movieResponse = movieResponseList.get(position);

        String year = movieResponse.getReleaseDate();
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500"+movieResponse.getPosterPath())
                .into(holder.iv_item_image);
        holder.tv_item_title.setText(movieResponse.getTitle());
        holder.tv_item_year.setText(year.substring(0,4));

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("backdrop", movieResponse.getBackdropPath());
            intent.putExtra("poster", movieResponse.getPosterPath());
            intent.putExtra("title", movieResponse.getTitle());
            intent.putExtra("year", movieResponse.getReleaseDate());
            intent.putExtra("rating", movieResponse.getVoteAverage());
            intent.putExtra("sinopsis", movieResponse.getOverview());
            intent.putExtra("m_or_t", "Movie");
            holder.itemView.getContext().startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return movieResponseList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_item_image;
        TextView tv_item_title, tv_item_year;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            iv_item_image = itemView.findViewById(R.id.iv_item_image);
            tv_item_title = itemView.findViewById(R.id.tv_item_title);
            tv_item_year = itemView.findViewById(R.id.tv_item_year);

        }
    }
}
