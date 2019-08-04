package com.example.MoviesRateApp.View.Fragments;

import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.MoviesRateApp.Model.MovieObj;
import com.example.MoviesRateApp.R;
import com.example.MoviesRateApp.View.Adapters.MoviesItemAdapter;
import com.example.MoviesRateApp.View.Fragments.ItemFragment;
import com.example.MoviesRateApp.View.MainActivity;

import java.util.ArrayList;


public class MoviesFragment extends Fragment {
    private MoviesItemAdapter adapter;
    private RecyclerView recycler;
    private LinearLayoutManager lManager;
    protected ArrayList<MovieObj> items;
    String imagesBaseUrl = "https://image.tmdb.org/t/p/w185_and_h278_bestv2";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        super.onCreate(savedInstanceState);

        return inflater.inflate(R.layout.recycle_view_fragment, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        recycler=view.findViewById(R.id.movies_recycleView);
        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recycler.setLayoutManager(lManager);
        ((MainActivity) getActivity()).getMoviesPresenter().getUpcommingMovies();
    }


    public void updateRecycleViewData(ArrayList<MovieObj> movies){

        items = movies;
        adapter = new MoviesItemAdapter(movies, imagesBaseUrl);
        adapter.setOnItemClickListener(new MoviesItemAdapter.MyViewHolder.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                MovieObj currentItem = items.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("type", "regular");
                bundle.putString("CloudId", currentItem.getCloudId());
                bundle.putString("title", currentItem.getTitle());
                bundle.putString("posterPath", imagesBaseUrl+currentItem.getPosterPath());
                bundle.putString("releaseDate", currentItem.getReleaseDate());
                bundle.putString("voteAverage", currentItem.getVoteAverage());
                bundle.putString("overview", currentItem.getOverview());

                ItemFragment nextFrag= new ItemFragment();
                nextFrag.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentPlaceHolder, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();

                FlingAnimation fling = new FlingAnimation(v, DynamicAnimation.SCROLL_X);
                fling.setStartVelocity(-20)
                        .setMinValue(0)
                        .setMaxValue(100)
                        .setFriction(1.1f)
                        .start();
            }
        });
        recycler.setAdapter(adapter);
    }
}

