package com.example.MoviesRateApp.View.Adapters;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.MoviesRateApp.Model.MovieObj;
import com.example.MoviesRateApp.R;
import com.example.MoviesRateApp.View.MainActivity;

import java.util.ArrayList;

public class MoviesItemAdapter extends RecyclerView.Adapter<MoviesItemAdapter.MyViewHolder> {

    private ArrayList<MovieObj> dataSet;
    private Context context;
    private String images_baseUrl;
    private static MyViewHolder.ClickListener clickListener;

    public MoviesItemAdapter(ArrayList<MovieObj> data, String path) {
        this.dataSet = data;
        images_baseUrl = path;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);
        context = parent.getContext();

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView titleTV = holder.titleTV;
        ImageView postarImage = holder.posterPath;

        titleTV.setText(dataSet.get(listPosition).getTitle());
        Log.i("img", images_baseUrl + dataSet.get(listPosition).getPosterPath());
        Glide.with(context).load(images_baseUrl + dataSet.get(listPosition).getPosterPath())
                .into(postarImage);


      if(images_baseUrl=="")
          holder.removeButton.setVisibility(View.VISIBLE);

        // Set a click listener for item remove button
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the clicked item label
//                String itemLabel = dataSet.get(listPosition);

                //remove movieObj from data base
                ((MainActivity) context).getMoviesPresenter()
                        .removeFavoriteMovie(dataSet.get(listPosition));

                // Remove the item on remove/button click
                dataSet.remove(listPosition);

                notifyItemRemoved(listPosition);
                notifyItemRangeChanged(listPosition, dataSet.size());

                // Show the removed item label
                //    Toast.makeText(mContext,"Removed : " + itemLabel,Toast.LENGTH_SHORT).show();


            }
        });


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView titleTV;
        ImageView posterPath;
        Button removeButton;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.titleTV = (TextView) itemView.findViewById(R.id.movie_titleTV);
            this.posterPath = (ImageView) itemView.findViewById(R.id.poster_path_IV);
            this.removeButton = (Button) itemView.findViewById(R.id.removeButton);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        public interface ClickListener {
            void onItemClick(int position, View v);
        }

    }

    public void setOnItemClickListener(MyViewHolder.ClickListener clickListener) {
        MoviesItemAdapter.clickListener = clickListener;
    }


}
