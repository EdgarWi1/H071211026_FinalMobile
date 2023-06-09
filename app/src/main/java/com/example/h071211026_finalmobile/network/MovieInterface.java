package com.example.h071211026_finalmobile.network;

import com.example.h071211026_finalmobile.model.MovieDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieInterface {

    @GET("movie/popular")
    Call<MovieDataResponse>
    getMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int pageIndex
    );

}