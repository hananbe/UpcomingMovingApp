package com.example.MoviesRateApp.Presenter;

import com.example.MoviesRateApp.Model.MovieItem;
import com.example.MoviesRateApp.Model.MovieObj;
import com.example.MoviesRateApp.Model.MoviesRepository;
import com.example.MoviesRateApp.View.MovieView;
import com.example.MoviesRateApp.service.MoviesService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesPresenter {

    private MovieView moviesView;
    private MoviesService moviesService;
    private MoviesRepository repository;


    public MoviesPresenter(MovieView view) {
        this.moviesView = view;
        moviesService = new MoviesService();
        repository = new MoviesRepository();
    }


    public void getFavoriteMovies() {
        ArrayList<MovieObj> list = new ArrayList<>();
        list.addAll(repository.getAllFavoriteMoviers());
        moviesView.favoriteMoviesReady(list);
    }


    public void getUpcommingMovies() {
        try {
            MoviesService movieService = new MoviesService();
            Call<MovieItem> call = movieService.getAPI().getLatestMovies();
            call.enqueue(new Callback<MovieItem>() {
                @Override
                public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
                    MovieItem responseItems = response.body();
                    List<MovieItem.items> list = responseItems.movies;
                    ArrayList<MovieObj> result = new ArrayList<>();
                    for (MovieItem.items item : list) {
                        MovieObj currentItem = new MovieObj(item.id, item.title, item.poster_path,
                                getYear(item.release_date), item.vote_average, item.overview);
                        result.add(currentItem);
                        moviesView.moviesReady(result);
                    }
                }

                @Override
                public void onFailure(Call<MovieItem> call, Throwable t) {
                }
            });
        } catch (Exception e) {
        }
    }

    private String getYear(String date) {
        String[] split = date.split("-");

        if (split.length == 3) return split[0];
        return "";
    }


    public void addMovie(MovieObj movie) {
        repository.addMovie(movie);
    }

    public boolean containMovie(MovieObj movie) {
        return repository.containMovie(movie);
    }

    public void removeFavoriteMovie(MovieObj movie) {
        repository.removeMovie(movie);
    }

}
