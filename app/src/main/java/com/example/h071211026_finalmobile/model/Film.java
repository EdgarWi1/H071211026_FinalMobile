package com.example.h071211026_finalmobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Film implements Parcelable {
    private int id;
    private String backdropPath, posterPath, name, rating, releaseDate, overview;
    
    public Film() {
        
    }
    
    public Film(int id, String backdropPath, String posterPath, String name, String rating, String release_date, String overview) {
        this.id = id;
        this.backdropPath = backdropPath;
        this.posterPath = posterPath;
        this.name = name;
        this.rating = rating;
        this.releaseDate = release_date;
        this.overview = overview;
    }
    protected Film(Parcel in) {
        id = in.readInt();
        backdropPath = in.readString();
        posterPath = in.readString();
        name = in.readString();
        rating = in.readString();
        releaseDate = in.readString();
        overview = in.readString();
    }
    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }@Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getbackdropPath() {
        return backdropPath;
    }

    public void setbackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getposterPath() {
        return posterPath;
    }

    public void setposterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String release_date) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(backdropPath);
        parcel.writeString(posterPath);
        parcel.writeString(name);
        parcel.writeString(rating);
        parcel.writeString(releaseDate);
        parcel.writeString(overview);
    }
}
