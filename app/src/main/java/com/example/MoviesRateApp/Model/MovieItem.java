package com.example.MoviesRateApp.Model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class MovieItem {
    @SerializedName( "results")
    public List<items> movies = new ArrayList();

    public class items {

        @SerializedName("poster_path")
        public String poster_path;
        @SerializedName("id")
        public String id;
        @SerializedName("title")
        public String title;
        @SerializedName("overview")
        public String overview;
        @SerializedName("release_date")
        public String release_date;
        @SerializedName("vote_average")
        public String vote_average;

    }


}