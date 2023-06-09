package com.example.h071211026_finalmobile.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.h071211026_finalmobile.R;
import com.example.h071211026_finalmobile.adapter.MovieAdapter;
import com.example.h071211026_finalmobile.model.MovieDataResponse;
import com.example.h071211026_finalmobile.model.MovieResponse;
import com.example.h071211026_finalmobile.network.MovieInstance;
import com.example.h071211026_finalmobile.network.MovieInterface;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieFragment extends Fragment {
    private static MovieFragment instance;

    private RecyclerView recyclerView;

    private ProgressBar progressBar;
    private LinearLayout errorNetwork;
    private GridLayoutManager gridLayoutManager;

    MovieAdapter movieAdapter;

    static ArrayList<MovieResponse> movieResponses = new ArrayList<>();

    public MovieFragment() {
        // Required empty public constructor
    }

    public static MovieFragment getInstance() {
        if (instance == null) {
            instance = new MovieFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = getActivity().findViewById(R.id.progressbar);
        recyclerView = getActivity().findViewById(R.id.rv_movie);
        getMovies();

    }

    public void getMovies(){
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = MovieInstance.getClient();
        MovieInterface movieInterface = retrofit.create(MovieInterface.class);
        Call<MovieDataResponse> client = movieInterface.getMovies("5803c86729e660aaa9f12fdc380b7158", "en-US", 1);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(() -> client.enqueue(new Callback<MovieDataResponse>() {
            @Override
            public void onResponse(Call<MovieDataResponse> call, Response<MovieDataResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ArrayList<MovieResponse> movies = (ArrayList<MovieResponse>) response.body().getResults();

                        handler.post(() -> {
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            MovieAdapter adapter = new MovieAdapter(movies);
                            recyclerView.setAdapter(adapter);
                            progressBar.setVisibility(View.GONE);
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieDataResponse> call, Throwable t) {
                showErrorNetwork();
            }

        }));
    }

    private void showErrorNetwork() {
        progressBar.setVisibility(View.GONE);
        errorNetwork.setVisibility(View.VISIBLE);

        errorNetwork.setOnClickListener(view -> {
            errorNetwork.setVisibility(View.GONE);
            getMovies();
        });
    }

}