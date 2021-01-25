package com.example.valoranttracker;

import com.example.valoranttracker.models.Profile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ValorantAPI {

    public static final String BASE_URL = "https://api.henrikdev.xyz";

    @Headers("Content-Type: application/json")
    @GET("/valorant/v1/profile/{name}/{tag}")
    Call<Profile> getData(@Path("name") String name, @Path("tag") String tag);
}
