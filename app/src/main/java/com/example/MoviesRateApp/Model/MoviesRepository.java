package com.example.MoviesRateApp.Model;

import com.orm.util.NamingHelper;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class MoviesRepository {
    HashMap<String, MovieObj> moviesList;

    public MoviesRepository() {
        moviesList = new LinkedHashMap<>();
    }

    public void addMovie(MovieObj movie) {
        List<MovieObj> movieInDB = MovieObj.find(MovieObj.class, NamingHelper.toSQLNameDefault("cloudId") + "= ?", movie.getCloudId());

        if (movieInDB.size() == 0) {
            movie.save();
            moviesList.put(movie.getCloudId(), movie);
        }
    }

    public void removeMovie(MovieObj movie) {
        List<MovieObj> movies = MovieObj.find(MovieObj.class, NamingHelper.toSQLNameDefault("cloudId") + "= ?", movie.getCloudId());

        for (int i = 0; i < movies.size(); i++) {
            movies.get(i).delete();
            moviesList.remove(movies.get(i));
        }

    }

    public List<MovieObj> getAllFavoriteMoviers() {
        return MovieObj.listAll(MovieObj.class);
    }

    public boolean containMovie(MovieObj movie) {
        //return moviesList.containsKey(movie.getCloudId());
return false;
    }

}