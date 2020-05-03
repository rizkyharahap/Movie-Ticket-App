package com.haphap.movieticketapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.haphap.movieticketapp.model.Movie;
import com.haphap.movieticketapp.model.MovieResult;
import com.haphap.movieticketapp.service.Api;
import com.haphap.movieticketapp.service.Client;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingViewModel extends AndroidViewModel {

    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> listMutableLiveData = new MutableLiveData<>();

    public UpcomingViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<List<Movie>> getListMutableLiveData() {
        Api apiService = Client.getService();
        Call<MovieResult> call = apiService.getUpcoming();
        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                MovieResult movieResult = response.body();
                movies= (ArrayList<Movie>) movieResult.getResults();
                listMutableLiveData.setValue(movies);
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {

            }
        });
        return listMutableLiveData;
    }

    public LiveData<List<Movie>> getAllMovie() {
        return getListMutableLiveData();
    }
}
