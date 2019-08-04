package com.example.MoviesRateApp.View.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.MoviesRateApp.Model.MovieObj;
import com.example.MoviesRateApp.R;
import com.example.MoviesRateApp.View.Adapters.MoviesItemAdapter;
import com.example.MoviesRateApp.View.MainActivity;

import java.util.ArrayList;


public class FavoriteFragment extends Fragment {
    View view;
    private RecyclerView recycler;
    private LinearLayoutManager lManager;
    private MoviesItemAdapter adapter;
    protected ArrayList<MovieObj> favoriteItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.recycle_view_fragment, parent, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recycler = view.findViewById(R.id.movies_recycleView);
        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recycler.setLayoutManager(lManager);

        ((MainActivity) getActivity()).getMoviesPresenter().getFavoriteMovies();
    }

    public void updateRecycleViewData(ArrayList<MovieObj> movies) {

        favoriteItems = movies;
        adapter = new MoviesItemAdapter(favoriteItems, "");
        adapter.setOnItemClickListener(new MoviesItemAdapter.MyViewHolder.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                MovieObj currentItem = favoriteItems.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("type", "favorite");
                bundle.putString("CloudId", currentItem.getCloudId());
                bundle.putString("title", currentItem.getTitle());
                bundle.putString("posterPath", currentItem.getPosterPath());
                bundle.putString("releaseDate", currentItem.getReleaseDate());
                bundle.putString("voteAverage", currentItem.getVoteAverage());
                bundle.putString("overview", currentItem.getOverview());

                ItemFragment nextFrag = new ItemFragment();
                nextFrag.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentPlaceHolder, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }


        });

        recycler.setAdapter(adapter);
    }


}

