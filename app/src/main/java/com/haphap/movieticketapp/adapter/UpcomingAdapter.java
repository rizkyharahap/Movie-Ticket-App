package com.haphap.movieticketapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.haphap.movieticketapp.R;
import com.haphap.movieticketapp.model.Movie;

import java.util.List;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.ViewHolder>{
    private List<Movie> movieList;
    private Context context;

    public UpcomingAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_upcomming, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.bindItem(movieList.get(position), context);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvReleaseDate;
        ConstraintLayout layoutUpcoming;
        View itemViewer;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvReleaseDate = itemView.findViewById(R.id.tv_release_date);
            layoutUpcoming = itemView.findViewById(R.id.layout_upcoming);
            itemViewer = itemView;
        }

        void bindItem(Movie movie, Context context) {
            tvTitle.setText(movie.getTitle());
            tvReleaseDate.setText(movie.getReleaseDate());
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w342" + movie.getPosterPath())
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            layoutUpcoming.setBackground(resource);
                        }
                    });

        }
    }
}
