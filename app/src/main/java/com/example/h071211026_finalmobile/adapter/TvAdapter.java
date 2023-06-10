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
import com.example.h071211026_finalmobile.model.TvResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder> {

    private final ArrayList<TvResponse> tvResponseLists;

    public TvAdapter(ArrayList<TvResponse> tvResponseLists) {
        this.tvResponseLists = tvResponseLists;
    }


    @NonNull
    @Override
    public TvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new TvAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TvAdapter.ViewHolder holder, int position) {

        String year = tvResponseLists.get(position).getReleaseDate();
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500"+tvResponseLists.get(position).getPosterPath())
                .into(holder.iv_item_image);
        holder.tv_item_title.setText(tvResponseLists.get(position).getTitle());
        holder.tv_item_year.setText(year.substring(0,4));

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("tv", tvResponseLists.get(position).getOverview());
            System.out.println(tvResponseLists.get(position).getOverview());
            holder.itemView.getContext().startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return tvResponseLists.size();
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
