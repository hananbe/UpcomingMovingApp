package com.example.MoviesRateApp.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.MoviesRateApp.Presenter.MoviesPresenter;
import com.example.MoviesRateApp.R;
import com.example.MoviesRateApp.Model.MovieObj;
import com.example.MoviesRateApp.View.Fragments.FavoriteFragment;
import com.example.MoviesRateApp.View.Fragments.MoviesFragment;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieView {

    private MoviesPresenter moviesPresenter;
    private Fragment fragment;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moviesPresenter = new MoviesPresenter(this);
        fragment = new MoviesFragment();
        setFragmentTransaction();
    }


    @Override
    public void moviesReady(ArrayList<MovieObj> movies) {
        ((MoviesFragment) fragment).updateRecycleViewData(movies);

    }


    @Override
    public void favoriteMoviesReady(ArrayList<MovieObj> movies) {
        ((FavoriteFragment) fragment).updateRecycleViewData(movies);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.home_button:
                fragment = new MoviesFragment();
                setFragmentTransaction();

                return true;
            case R.id.favorite_button:
                fragment = new FavoriteFragment();
                setFragmentTransaction();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public MoviesPresenter getMoviesPresenter() {
        return moviesPresenter;
    }

    private void setFragmentTransaction() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentPlaceHolder, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

