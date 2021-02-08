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

import com.example.valoranttracker.adapters.CareerAdapter;
import com.example.valoranttracker.MainActivity;
import com.example.valoranttracker.R;
import com.example.valoranttracker.ValorantAPI;
import com.example.valoranttracker.models.CareerModel;
import com.example.valoranttracker.models.Matches.Matches;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayerCareerFragment extends Fragment {

    public static final String TAG = "PlayerCareerFragment";
    public static final String BASE_URL = "https://api.henrikdev.xyz";

    private RecyclerView recyclerView;
    private CareerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_career_fragment, container, false);

        recyclerView = view.findViewById(R.id.playerCareerFragRecyclerView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ValorantAPI valorantAPI = retrofit.create(ValorantAPI.class);
        Call<Matches> call = valorantAPI.getMatchesData("RKtheGREAT007","8660");

        call.enqueue(new Callback<Matches>() {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {

                if(!response.body().getStatus().equals("200")) {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }

                Log.d(TAG, "onResponse: " + response.body().toString());

                ArrayList<CareerModel> arrayList = new ArrayList<>();
                for(int i =0; i < response.body().getMatches().size(); i++) {
                    if(!response.body().getMatches().get(i).isAvailable()) {
                        continue;
                    }
                    CareerModel careerModel = new CareerModel();
                    careerModel.setAgent(getResourceId(response.body().getMatches().get(i).getMetadata().getAgentplayed().toLowerCase(),
                            "drawable", getActivity().getPackageName()));
                    careerModel.setMap(response.body().getMatches().get(i).getMetadata().getMap());
                    careerModel.setModeSrc(getResourceId(response.body().getMatches().get(i).getMetadata().getModename().toLowerCase(),
                            "drawable", getActivity().getPackageName()));
                    careerModel.setMode(response.body().getMatches().get(i).getMetadata().getModename());
                    careerModel.setKda(response.body().getMatches().get(i).getGame().getKda().getKda());
                    careerModel.setGameid(response.body().getMatches().get(i).getMetadata().getGameid());
                    careerModel.setPlayerhaswon(response.body().getMatches().get(i).getMetadata().isPlayerhaswon());
                    careerModel.setRoundslost(response.body().getMatches().get(i).getGame().getRoundslost());
                    careerModel.setRoundswon(response.body().getMatches().get(i).getGame().getRoundswon());
                    careerModel.setTimestamp(response.body().getMatches().get(i).getMetadata().getTimestamp());

                    arrayList.add(careerModel);
                }

                adapter = new CareerAdapter(getContext(), arrayList);

                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setHasFixedSize(true);


            }

            @Override
            public void onFailure(Call<Matches> call, Throwable t) {

            }
        });


        return view;
    }

    private int getResourceId(String variableName, String resourceName, String packageName) {
        Log.d(TAG, "getResourceId: " + variableName + " " + getResources().getIdentifier(variableName, resourceName, packageName));
        return getResources().getIdentifier(variableName, resourceName, packageName);

    }
}
