package com.haphap.movieticketapp.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.haphap.movieticketapp.activity.DetailMovieActivity;
import com.haphap.movieticketapp.model.Movie;

import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>{
    private List<Movie> movieList;
    private Context context;

    public NowPlayingAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_now_playing, parent, false));
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

        TextView tvTitle, tvVoteAverage;
        ConstraintLayout layoutNowPlaying;
        View itemViewer;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvVoteAverage = itemView.findViewById(R.id.tv_vote_average);
            layoutNowPlaying = itemView.findViewById(R.id.layout_now_playing);
            itemViewer = itemView;
        }

        void bindItem(final Movie movie, final Context context) {
            tvTitle.setText(movie.getTitle());
            tvVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w342" + movie.getPosterPath())
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            layoutNowPlaying.setBackground(resource);
                        }
                    });

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.class.getSimpleName(), movie);
                context.startActivity(intent);
            });
        }
    }
}
