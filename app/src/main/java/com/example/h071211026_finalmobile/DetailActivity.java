package com.example.h071211026_finalmobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DetailActivity extends AppCompatActivity {

    private FloatingActionButton back;

    private FloatingActionButton favorite;

    private ImageView iv_backdropcard, iv_imageCard;
    private TextView tv_titleDetail, tv_yearDetail, tv_rating, tv_sinopsis, tv_type;

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

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500" + getIntent().getStringExtra("backdrop"))
                .into(iv_backdropcard);
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500" + getIntent().getStringExtra("poster"))
                .into(iv_imageCard);
        tv_titleDetail.setText(getIntent().getStringExtra("title"));

        String dt = getIntent().getStringExtra("year");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd LLLL yyyy");
        String dateTime = simpleDateFormat.format(calendar.getTime()).toString();
        tv_yearDetail.setText(dateTime);

        tv_type.setText(getIntent().getStringExtra("m_or_t"));

        tv_rating.setText(String.valueOf(getIntent().getDoubleExtra("rating",0)));

        tv_sinopsis.setText(getIntent().getStringExtra("sinopsis"));


//        String sinopsis = getIntent().getStringExtra("sinopsis");
//        System.out.println(sinopsis);


//        if (sinopsis == ""){
//            tv_sinopsis.setText("-");
//        }
//        else {
//            tv_sinopsis.setText(getIntent().getStringExtra("sinopsis"));
//        }


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
}