package com.example.h071211026_finalmobile.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.h071211026_finalmobile.R;
import com.example.h071211026_finalmobile.adapter.FavoriteAdapter;
import com.example.h071211026_finalmobile.adapter.MovieAdapter;
import com.example.h071211026_finalmobile.database.FilmHelper;
import com.example.h071211026_finalmobile.model.Favorite;
import com.example.h071211026_finalmobile.model.MovieDataResponse;
import com.example.h071211026_finalmobile.model.MovieResponse;
import com.example.h071211026_finalmobile.network.MovieInstance;
import com.example.h071211026_finalmobile.network.MovieInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FavoriteFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private GridLayoutManager gridLayoutManager;

    private static FavoriteFragment instance;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment getInstance() {
        if (instance == null) {
            instance = new FavoriteFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = getActivity().findViewById(R.id.progressbar);
        recyclerView = getActivity().findViewById(R.id.rv_movie);
        getFavorite();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        FavoriteAdapter adapter = new FavoriteAdapter(getFavorite());
        recyclerView.setAdapter(adapter);

        progressBar.setVisibility(View.GONE);

    }

    public List<Favorite> getFavorite(){
        progressBar.setVisibility(View.VISIBLE);
        FilmHelper filmHelper = new FilmHelper(getContext());

        List<Favorite> list = new ArrayList<>();
        SQLiteDatabase db = FilmHelper.openRead();

        Cursor cursor = filmHelper.queryAll();
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            String backdrop = cursor.getString(cursor.getColumnIndexOrThrow("backdrop"));
            String year = cursor.getString(cursor.getColumnIndexOrThrow("year"));
            String poster = cursor.getString(cursor.getColumnIndexOrThrow("poster"));
            Double rating = cursor.getDouble((cursor.getColumnIndexOrThrow("rating")));
            String overview = cursor.getString(cursor.getColumnIndexOrThrow("overview"));
            Favorite fav = new Favorite(backdrop, poster,title, rating, year, overview);
            list.add(fav);
        }
        cursor.close();
        db.close();

        return list;

    }
}