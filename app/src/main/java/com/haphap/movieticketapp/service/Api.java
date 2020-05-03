package com.haphap.movieticketapp.service;

import com.haphap.movieticketapp.model.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String API_KEY = "237893d501833425b891960da4b1da9c";

    @GET("movie/now_playing?api_key=" + API_KEY)
    Call<MovieResult> getNowPlaying();

    @GET("movie/upcoming?api_key=" + API_KEY)
    Call<MovieResult> getUpcoming();

    @GET("search/movie?api_key="+ API_KEY)
    Call<MovieResult> getSearchMovie(@Query("query") String string);
}
