package com.example.h071211026_finalmobile;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.h071211026_finalmobile.database.DatabaseContract;
import com.example.h071211026_finalmobile.database.DatabaseHelper;
import com.example.h071211026_finalmobile.database.FilmHelper;
import com.example.h071211026_finalmobile.model.Film;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DetailActivity extends AppCompatActivity {

    private Film film;

    private ImageView iv_backdropcard, iv_imageCard;
    private TextView tv_titleDetail, tv_yearDetail, tv_rating, tv_sinopsis, tv_type;
    private FloatingActionButton  btn_favorite, btn_back;

    public static final String EXTRA_FILM = "extra_film";
    public static final int RESULT_ADD = 101;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;
    
    private Boolean isFav;

    FilmHelper filmHelper = new FilmHelper(DetailActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        iv_backdropcard = findViewById(R.id.iv_backdropcard);
        iv_imageCard = findViewById(R.id.iv_imagecard);
        tv_titleDetail = findViewById(R.id.tv_titleDetail);
        tv_yearDetail = findViewById(R.id.tv_yearDetail);
        tv_rating = findViewById(R.id.tv_rating);
        tv_sinopsis = findViewById(R.id.tv_sinopsis);
        tv_type = findViewById(R.id.tv_type);
        btn_favorite = findViewById(R.id.btn_favorite);
        btn_back = findViewById(R.id.btn_back);

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500" + getIntent().getStringExtra("backdrop"))
                .into(iv_backdropcard);
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500" + getIntent().getStringExtra("poster"))
                .into(iv_imageCard);
        tv_titleDetail.setText(getIntent().getStringExtra("title"));
        tv_yearDetail.setText(getIntent().getStringExtra("year"));
        tv_type.setText(getIntent().getStringExtra("m_or_t"));
        tv_rating.setText(String.valueOf(getIntent().getDoubleExtra("rating",0)));
        tv_sinopsis.setText(getIntent().getStringExtra("sinopsis"));

        btn_favorite.setOnClickListener(view -> {
            if (isFavorite(getIntent().getStringExtra("title"))) {
                delete(getIntent().getStringExtra("title"));
            }
            else {
                save();
            }
        });


    }

    private void save() {
        ContentValues values = new ContentValues();

        FilmHelper.open();

        values.put(DatabaseContract.FilmColumns.BACKDROP_PATH, getIntent().getStringExtra("backdrop"));
        values.put(DatabaseContract.FilmColumns.POSTER_PATH, getIntent().getStringExtra("poster"));
        values.put(DatabaseContract.FilmColumns.NAME, getIntent().getStringExtra("title"));
        values.put(DatabaseContract.FilmColumns.OVERVIEW, getIntent().getStringExtra("overview"));
        values.put(DatabaseContract.FilmColumns.RATING, getIntent().getDoubleExtra("rating", 0));
        values.put(DatabaseContract.FilmColumns.RELEASE_DATE, getIntent().getStringExtra("year"));
        filmHelper.insert(values);
        Toast.makeText(this, "Added To Favorite", Toast.LENGTH_SHORT).show();

        FilmHelper.close();
    }

    private void delete(String title) {

        FilmHelper.open();
        filmHelper.deleteByTitle(title);
        Toast.makeText(this, "Removed To Favorite", Toast.LENGTH_SHORT).show();
        FilmHelper.close();

    }

    public boolean isFavorite(String title) {

        SQLiteDatabase db = FilmHelper.openRead();
        Cursor cursor = db.rawQuery("SELECT * FROM favorites WHERE id = ?", new String[]{String.valueOf(title)});
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }
    }
}