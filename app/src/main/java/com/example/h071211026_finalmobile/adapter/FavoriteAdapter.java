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
import com.example.h071211026_finalmobile.model.Favorite;
import com.example.h071211026_finalmobile.model.MovieResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private final List<Favorite> favorites;

    public FavoriteAdapter(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_layout, parent, false);
        return new FavoriteAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        Favorite favorite = favorites.get(position);

        String year = favorite.getReleaseDate();
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500"+favorite.getPosterPath())
                .into(holder.iv_fav_image);
        holder.tv_fav_title.setText(favorite.getName());
        holder.tv_fav_year.setText(year.substring(0,4));

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("backdrop", favorite.getBackdropPath());
            intent.putExtra("poster", favorite.getPosterPath());
            intent.putExtra("title", favorite.getName());
            intent.putExtra("year", favorite.getReleaseDate());
            intent.putExtra("rating", favorite.getRating());
            intent.putExtra("sinopsis", favorite.getOverview());
            intent.putExtra("m_or_t", "Movie");
            holder.itemView.getContext().startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_fav_image;
        TextView tv_fav_title, tv_fav_year;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            iv_fav_image = itemView.findViewById(R.id.iv_fav_image);
            tv_fav_title = itemView.findViewById(R.id.tv_fav_title);
            tv_fav_year = itemView.findViewById(R.id.tv_fav_year);

        }
    }
}
