package com.example.allnews;

import com.example.allnews.Models.HeadLines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("top-headlines")
    Call<HeadLines> getHeadlines(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<HeadLines> getSpecificData(
            @Query("q") String query,
            @Query("apiKey") String apiKey
    );



}
