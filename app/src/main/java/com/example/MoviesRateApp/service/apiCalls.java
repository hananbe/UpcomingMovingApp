package com.example.MoviesRateApp.service;

import com.example.MoviesRateApp.Model.MovieItem;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiCalls {
    //you should replace some key with your acess token from tmdb.com
    @GET("movie/upcoming?api_key=some key")
    Call<MovieItem> getLatestMovies();
}