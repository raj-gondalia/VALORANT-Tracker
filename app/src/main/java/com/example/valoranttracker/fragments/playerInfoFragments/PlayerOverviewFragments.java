package com.example.valoranttracker.fragments.playerInfoFragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttracker.adapters.OverviewAdapter;
import com.example.valoranttracker.MainActivity;
import com.example.valoranttracker.R;
import com.example.valoranttracker.ValorantAPI;
import com.example.valoranttracker.models.OverviewModel;
import com.example.valoranttracker.models.Profile.Profile;
import com.example.valoranttracker.models.Puuid.Puuid;
import com.example.valoranttracker.models.RealmModel;

import java.util.ArrayList;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayerOverviewFragments extends Fragment {

    private static final String TAG = "PlayerOverviewFragments";
    public static final String BASE_URL = "https://api.henrikdev.xyz";

    private RecyclerView recyclerView;

    private OverviewAdapter overviewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_overview_fragment, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recyclerView = view.findViewById(R.id.playerOverviewRecyclerView);

        Bundle bundle = getActivity().getIntent().getExtras();
        String gameName = bundle.getString("gameName");
        String tag = bundle.getString("tag");

        ValorantAPI valorantAPI = retrofit.create(ValorantAPI.class);
        Call<Profile> call = valorantAPI.getProfileData(gameName,tag);

        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
//                Log.d(TAG, "onResponse: " + response.toString());
//                Log.d(TAG, "onResponse: " + response.body().toString());

                if(!response.body().getStatus().equals("200")) {

                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }

                String puuid = getPuuid(gameName, tag);

                saveData(gameName, tag, puuid);

                ArrayList<OverviewModel> arrayList = new ArrayList<>();
                arrayList.add(new OverviewModel("playtime", response.body().getStats().getPlaytime().getPlaytimepatched()));
                arrayList.add(new OverviewModel("matches", Integer.toString(response.body().getStats().getMatches())));
                arrayList.add(new OverviewModel("kills", Integer.toString(response.body().getStats().getKills())));
                arrayList.add(new OverviewModel("deathes", Integer.toString(response.body().getStats().getDeaths())));
                arrayList.add(new OverviewModel("assits", Integer.toString(response.body().getStats().getAssists())));
                arrayList.add(new OverviewModel("kd ratio", Double.toString(response.body().getStats().getKdratio())));
                arrayList.add(new OverviewModel("headshots", Integer.toString(response.body().getStats().getHeadshots())));
                arrayList.add(new OverviewModel("headshot %", Double.toString(response.body().getStats().getHeadshotpercentage())));
                arrayList.add(new OverviewModel("wins", Integer.toString(response.body().getStats().getWins())));
                arrayList.add(new OverviewModel("win %", Double.toString(response.body().getStats().getWinpercentage())));
                arrayList.add(new OverviewModel("firstbloods", Integer.toString(response.body().getStats().getFirstbloods())));
                arrayList.add(new OverviewModel("aces", Integer.toString(response.body().getStats().getAces())));
                arrayList.add(new OverviewModel("clutches", Integer.toString(response.body().getStats().getClutches())));
                arrayList.add(new OverviewModel("flawless", Integer.toString(response.body().getStats().getFlawless())));


                overviewAdapter = new OverviewAdapter(getActivity(), arrayList);

                recyclerView.setAdapter(overviewAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private String getPuuid(String gameName, String tag) {

        final String[] puuid = new String[1];

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ValorantAPI valorantAPI = retrofit.create(ValorantAPI.class);
        Call<Puuid> call = valorantAPI.getPuuidData(gameName, tag);

        call.enqueue(new Callback<Puuid>() {
            @Override
            public void onResponse(Call<Puuid> call, Response<Puuid> response) {
                puuid[0] = response.body().getData().getPuuid();
            }

            @Override
            public void onFailure(Call<Puuid> call, Throwable t) {

            }
        });

        return puuid[0];
    }

    private void saveData(String gameName, String tag, String puuid) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmModel realmModel = realm.createObject(RealmModel.class);
                realmModel.setGameName(gameName);
                realmModel.setTag(tag);
                realmModel.setPlayerId(puuid);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "onSuccess: Data Saved");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d(TAG, "onError: " + error.getMessage());
            }
        });

    }

}