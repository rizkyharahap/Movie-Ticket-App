package com.haphap.movieticketapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.haphap.movieticketapp.R;
import com.haphap.movieticketapp.adapter.NowPlayingAdapter;
import com.haphap.movieticketapp.adapter.UpcomingAdapter;
import com.haphap.movieticketapp.viewmodel.NowPlayingViewModel;
import com.haphap.movieticketapp.viewmodel.UpcomingViewModel;

public class HomeFragment extends Fragment {

    private NowPlayingAdapter nowPlayingAdapter;
    private UpcomingAdapter upcomingAdapter;
    private RecyclerView rvNowPlaying, rvUpcomming;
    private ConstraintLayout layoutHome;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        rvNowPlaying = root.findViewById(R.id.rv_now_playing);
        rvUpcomming = root.findViewById(R.id.rv_upcomming);
        layoutHome = root.findViewById(R.id.layout_home);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NowPlayingViewModel nowPlayingViewModel = ViewModelProviders.of(this).get(NowPlayingViewModel.class);
        nowPlayingViewModel.getAllMovie().observe(getViewLifecycleOwner(), movies -> {
            nowPlayingAdapter = new NowPlayingAdapter(movies, getActivity());
            rvNowPlaying.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            rvNowPlaying.setAdapter(nowPlayingAdapter);
        });

        UpcomingViewModel upcomingViewModel = ViewModelProviders.of(this).get(UpcomingViewModel.class);
        upcomingViewModel.getAllMovie().observe(getViewLifecycleOwner(), movies -> {
            upcomingAdapter = new UpcomingAdapter(movies, getActivity());
            rvUpcomming.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            rvUpcomming.setAdapter(upcomingAdapter);
        });
    }
}
