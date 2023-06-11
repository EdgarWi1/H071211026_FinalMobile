package com.example.h071211026_finalmobile.model;

import com.example.h071211026_finalmobile.database.DatabaseContract;

public class Favorite {

    private String backdropPath;

    public Favorite(String backdropPath, String posterPath, String name, Double rating, String releaseDate, String overview) {
        this.backdropPath = backdropPath;
        this.posterPath = posterPath;
        this.name = name;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }

    private String posterPath;
    private String name;
    private Double rating;
    private String releaseDate;
    private String overview;


    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
