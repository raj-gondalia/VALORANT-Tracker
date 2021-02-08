package com.example.valoranttracker;

import com.example.valoranttracker.models.Leaderboard.Leaderboard;
import com.example.valoranttracker.models.Matches.Matches;
import com.example.valoranttracker.models.Profile.Profile;
import com.example.valoranttracker.models.Rank.Rank;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ValorantAPI {

    public static final String BASE_URL = "https://api.henrikdev.xyz";

    @Headers("Content-Type: application/json")
    @GET("/valorant/v1/profile/{name}/{tag}")
    Call<Profile> getProfileData(@Path("name") String name, @Path("tag") String tag);

    @Headers("Content-Type: application/json")
    @GET("/valorant/v1/matches/{name}/{tag}")
    Call<Matches> getMatchesData(@Path("name") String name, @Path("tag") String tag);

    @Headers("Content-Type: application/json")
    @GET("/valorant/v1/mmr/{region}/{name}/{tag}")
    Call<Rank> getRankData(@Path("region") String region, @Path("name") String name, @Path("tag") String tag);

    @Headers("Content-Type: application/json")
    @GET("/valorant/v1/leaderboard/{region}")
    Call<Leaderboard> getLeaderboardData(@Path("region") String region);
}
