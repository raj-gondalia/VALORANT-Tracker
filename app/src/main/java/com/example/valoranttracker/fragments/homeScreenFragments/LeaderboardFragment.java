package com.example.valoranttracker.fragments.homeScreenFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttracker.R;
import com.example.valoranttracker.net.ValorantAPI;
import com.example.valoranttracker.adapters.LeaderboardAdapter;
import com.example.valoranttracker.net.Leaderboard.Leaderboard;
import com.example.valoranttracker.net.Leaderboard.LeaderboardData;
import com.example.valoranttracker.models.LeaderboardModel;
import com.example.valoranttracker.sharedPreferences.SharedPref;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.view.View.GONE;

public class LeaderboardFragment extends Fragment {

    public static final String TAG = "LeaderboardFragment";
    public static final String BASE_URL = "https://api.henrikdev.xyz";

    private RecyclerView recyclerView;
    private LinearLayout linearLayout;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.leaderboard_fragment, container, false);

        recyclerView = view.findViewById(R.id.leaderboardFragRecyclerView);
        progressBar = view.findViewById(R.id.leaderboardFragProgressBar);
        linearLayout = view.findViewById(R.id.leaderboardFragLinearView);

        progressBar.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        String region = SharedPref.getInstance(getContext()).getRegion();

        ValorantAPI valorantAPI = retrofit.create(ValorantAPI.class);

        valorantAPI.getLeaderboardData(region)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Leaderboard>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Leaderboard leaderboard) {
                        ArrayList<LeaderboardData> leaderboardDataArrayList = leaderboard.getData();
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

                        progressBar.setVisibility(GONE);
                        linearLayout.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);

                        LeaderboardAdapter adapter = new LeaderboardAdapter(getContext(), leaderboardModels);

                        recyclerView.setAdapter(adapter);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onFailure: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return(view);
    }
}
