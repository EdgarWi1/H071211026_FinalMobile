package com.example.h071211026_finalmobile.network;

import com.example.h071211026_finalmobile.model.TvDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TvInterface {

    @GET("tv/popular")
    Call<TvDataResponse>
    getTvs(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int pageIndex
    );

}