package com.example.h071211026_finalmobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private FloatingActionButton back;

    private FloatingActionButton favorite;

    private ImageView iv_backdropcard, iv_imageCard;
    private TextView tv_titleDetail, tv_yearDetail, tv_rating, tv_sinopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tv_sinopsis = findViewById(R.id.tv_sinopsis);
        String sinopsis = getIntent().getStringExtra("tv");
//        loadFilmDetail(sinopsis);
        tv_sinopsis.setText(sinopsis);
        System.out.println(sinopsis);

//        back.findViewById(R.id.back)
//                .setOnClickListener(v -> {
//                    onBackPressed();
//                });
//
//        favorite.findViewById(R.id.favorite)
//                .setOnClickListener(v -> {
//                    //add to databaseee
//                });
    }

    private void loadFilmDetail(String tvResponse) {

        iv_backdropcard = findViewById(R.id.iv_backdropcard);
        iv_imageCard = findViewById(R.id.iv_imagecard);
        tv_titleDetail = findViewById(R.id.tv_titleDetail);
        tv_yearDetail = findViewById(R.id.tv_yearDetail);
        tv_rating = findViewById(R.id.tv_rating);


        // Update UI elements here
//        Picasso.get()
//                .load("https://image.tmdb.org/t/p/w500" + tvResponse.getBackdropPath())
//                .into(iv_backdropcard);
//        Picasso.get()
//                .load("https://image.tmdb.org/t/p/w500" + tvResponse.getPosterPath())
//                .into(iv_imageCard);
//        tv_titleDetail.setText(tvResponse.getTitle());
//        String dt = tvResponse.getReleaseDate();
//        tv_yearDetail.setText(dt);
//        tv_rating.setText(String.valueOf(tvResponse.getVoteAverage()));


    }
}