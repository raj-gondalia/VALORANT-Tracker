package com.example.valoranttracker.net;

import com.example.valoranttracker.net.Leaderboard.Leaderboard;
import com.example.valoranttracker.net.Match.Match;
import com.example.valoranttracker.net.Matches.Matches;
import com.example.valoranttracker.net.Profile.Profile;
import com.example.valoranttracker.net.Puuid.Puuid;
import com.example.valoranttracker.net.Rank.Rank;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ValorantAPI {

    public static final String BASE_URL = "https://api.henrikdev.xyz";

    @Headers("Content-Type: application/json")
    @GET("/valorant/v1/profile/{name}/{tag}")
    Observable<Profile> getProfileData(@Path("name") String name, @Path("tag") String tag);

    @Headers("Content-Type: application/json")
    @GET("/valorant/v1/matches/{name}/{tag}")
    Observable<Matches> getMatchesData(@Path("name") String name, @Path("tag") String tag);

    @Headers("Content-Type: application/json")
    @GET("/valorant/v1/mmr/{region}/{name}/{tag}")
    Observable<Rank> getRankData(@Path("region") String region, @Path("name") String name, @Path("tag") String tag);

    @Headers("Content-Type: application/json")
    @GET("/valorant/v1/leaderboard/{region}")
    Observable<Leaderboard> getLeaderboardData(@Path("region") String region);

    @Headers("Content-Type: application/json")
    @GET("/valorant/v1/match/{match-id}")
    Observable<Match> getMatchData(@Path("match-id") String matchId);

    @Headers("Content-Type: application/json")
    @GET("/valorant/v1/puuid/{name}/{tag}")
    Observable<Puuid> getPuuidData(@Path("name") String name, @Path("tag") String tag);
}
