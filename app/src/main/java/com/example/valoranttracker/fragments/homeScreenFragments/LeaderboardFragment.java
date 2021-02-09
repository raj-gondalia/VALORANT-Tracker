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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttracker.R;
import com.example.valoranttracker.ValorantAPI;
import com.example.valoranttracker.adapters.LeaderboardAdapter;
import com.example.valoranttracker.models.Leaderboard.Leaderboard;
import com.example.valoranttracker.models.Leaderboard.LeaderboardData;
import com.example.valoranttracker.models.LeaderboardModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LeaderboardFragment extends Fragment {

    public static final String TAG = "LeaderboardFragment";
    public static final String BASE_URL = "https://api.henrikdev.xyz";

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.leaderboard_fragment, container, false);

        recyclerView = view.findViewById(R.id.leaderboardFragRecyclerView);

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

                ArrayList<LeaderboardData> leaderboardDataArrayList = response.body().getData();
                ArrayList<LeaderboardModel> leaderboardModels = new ArrayList<>();
                for(int i = 0; i < 100; i++) {

                    LeaderboardModel leaderboardModel = new LeaderboardModel();

                    leaderboardModel.setGameName(leaderboardDataArrayList.get(i).getGameName());
                    leaderboardModel.setNumberOfWins(leaderboardDataArrayList.get(i).getNumberOfWins());
                    leaderboardModel.setRank(leaderboardDataArrayList.get(i).getLeaderboardRank());
                    leaderboardModel.setRankedRating(leaderboardDataArrayList.get(i).getRankedRating());
                    leaderboardModel.setTagLine(leaderboardDataArrayList.get(i).getTagLine());

                    leaderboardModels.add(leaderboardModel);
                }

                LeaderboardAdapter adapter = new LeaderboardAdapter(getContext(), leaderboardModels);

                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<Leaderboard> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return(view);
    }
}
