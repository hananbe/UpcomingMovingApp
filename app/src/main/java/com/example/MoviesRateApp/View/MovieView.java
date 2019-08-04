package com.example.MoviesRateApp.View;

import com.example.MoviesRateApp.Model.MovieObj;

import java.util.ArrayList;

public interface MovieView {

        void moviesReady(ArrayList<MovieObj> movies);
        void favoriteMoviesReady(ArrayList<MovieObj> movies);
    }


