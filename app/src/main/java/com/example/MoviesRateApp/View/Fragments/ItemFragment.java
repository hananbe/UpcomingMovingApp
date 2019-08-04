package com.example.MoviesRateApp.View.Fragments;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.MoviesRateApp.Model.MovieObj;
import com.example.MoviesRateApp.Presenter.MoviesPresenter;
import com.example.MoviesRateApp.R;
import com.example.MoviesRateApp.View.MainActivity;

public class ItemFragment extends Fragment {
    private MoviesPresenter presenter;
    private MovieObj movie;
    private Button addMovieButton;
    private TextView titleTV, averageRateTV, yearTV, overviewTV;
    private ImageView posterIV;

    View currentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentView = inflater.inflate(R.layout.movie_item, parent, false);
        return currentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = ((MainActivity) getActivity()).getMoviesPresenter();
        addMovieButton = view.findViewById(R.id.addToFavorite);
        titleTV = view.findViewById(R.id.titleTV);
        averageRateTV = view.findViewById(R.id.rateTV);
        yearTV = view.findViewById(R.id.yearTV);
        overviewTV = view.findViewById(R.id.overviewTV);
        posterIV = view.findViewById(R.id.posterIV);

        restoringIntentData();
        startAnimation();
    }

    //start activity animation
    private void startAnimation() {
        ObjectAnimator animation = ObjectAnimator.ofFloat(currentView.findViewById(R.id.scrollView), View.ALPHA, 0, 1);
        animation.setDuration(2000);
        animation.start();
    }

    //restoring data from bundle to create movieObj
    private void restoringIntentData() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String type = bundle.getString("type");
            String cloudId = bundle.getString("CloudId");
            String title = bundle.getString("title");
            String averageRate = bundle.getString("voteAverage");
            String year = bundle.getString("releaseDate");
            String overview = bundle.getString("overview");
            String posterPath = bundle.getString("posterPath");

            //set views
            titleTV.setText(title);
            averageRateTV.setText(averageRate);
            yearTV.setText(year);
            overviewTV.setText(overview);

            Glide.with(this).load(posterPath)
                    .into(posterIV);

            //creating movieObj
            movie = new MovieObj(cloudId, title, posterPath, year, averageRate, overview);


            //check if movie already exist in database
            boolean favoriteMovie = ((MainActivity) getActivity()).getMoviesPresenter()
                    .containMovie(movie);
            if (favoriteMovie)
                addMovieButton.setVisibility(View.INVISIBLE);
            else
                addMovieButton.setVisibility(View.VISIBLE);


            addMovieButton.setText("add to favorite");
            addMovieButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.addMovie(movie);
                    addMovieButton.setVisibility(View.INVISIBLE);
                }
            });
        }


    }

}
