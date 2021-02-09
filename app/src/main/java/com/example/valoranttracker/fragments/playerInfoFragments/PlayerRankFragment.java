package com.example.valoranttracker.fragments.playerInfoFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.valoranttracker.R;
import com.example.valoranttracker.ValorantAPI;
import com.example.valoranttracker.models.Rank.Rank;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.view.View.GONE;

public class PlayerRankFragment extends Fragment {

    private static final String TAG = "PlayerRankFragment";
    private static final String BASE_URL = "https://api.henrikdev.xyz";

    private TextView eloTextView, rankTextView, rankingInTierTextView, rankRatingText;
    private SeekBar seekBar;
    private ImageView rankImageView;
    private ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_rank_fragment, container, false);

        eloTextView = view.findViewById(R.id.playerRankFragEloTextView);
        rankImageView = view.findViewById(R.id.playerRankFragImageView);
        seekBar = view.findViewById(R.id.playerRankFragSeekBar);
        rankingInTierTextView = view.findViewById(R.id.playerRankFragRankingInTierTextView);
        rankTextView = view.findViewById(R.id.playerRankFragRankTextView);
        progressBar = view.findViewById(R.id.playerRankFragProgressBar);
        rankRatingText = view.findViewById(R.id.playerRankFragRankRatingText);

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ValorantAPI valorantAPI = retrofit.create(ValorantAPI.class);
        Call<Rank> call = valorantAPI.getRankData("ap", "RKtheGREAT007", "8660");

        call.enqueue(new Callback<Rank>() {
            @Override
            public void onResponse(Call<Rank> call, Response<Rank> response) {
//                Log.d(TAG, "onResponse: " + response.body().toString());

                progressBar.setVisibility(GONE);
                seekBar.setVisibility(View.VISIBLE);
                eloTextView.setVisibility(View.VISIBLE);
                rankImageView.setVisibility(View.VISIBLE);
                rankingInTierTextView.setVisibility(View.VISIBLE);
                rankTextView.setVisibility(View.VISIBLE);
                rankRatingText.setVisibility(View.VISIBLE);

                rankRatingText.setText("Rank Rating");
                seekBar.setProgress(response.body().getData().getRankingInTier());
                eloTextView.setText("Elo - " + response.body().getData().getElo());
                rankTextView.setText(response.body().getData().getCurrentTierPatched());
                rankingInTierTextView.setText(response.body().getData().getRankingInTier() + "/100");

                String tier = response.body().getData().getCurrentTierPatched().toLowerCase();
                tier = tier.replace(" ","");

                Log.d(TAG, "onResponse: " + tier);

                rankImageView.setImageResource(getResourceId(tier, "drawable", getActivity().getPackageName()));
            }

            @Override
            public void onFailure(Call<Rank> call, Throwable t) {

                progressBar.setVisibility(GONE);
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    private int getResourceId(String variableName, String resourceName, String packageName) {
        Log.d(TAG, "getResourceId: " + variableName + " " + getResources().getIdentifier(variableName, resourceName, packageName));
        return getResources().getIdentifier(variableName, resourceName, packageName);

    }
}
