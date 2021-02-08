package com.example.valoranttracker.fragments.homeScreenFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.valoranttracker.R;
import com.example.valoranttracker.ValorantAPI;
import com.example.valoranttracker.models.Leaderboard.Leaderboard;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LeaderboardFragment extends Fragment {

    public static final String TAG = "LeaderboardFragment";
    public static final String BASE_URL = "https://api.henrikdev.xyz";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.leaderboard_fragment, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ValorantAPI valorantAPI = retrofit.create(ValorantAPI.class);

        Call<Leaderboard> call = valorantAPI.getLeaderboardData("na");

        call.enqueue(new Callback<Leaderboard>() {
            @Override
            public void onResponse(Call<Leaderboard> call, Response<Leaderboard> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<Leaderboard> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return(view);
    }
}
