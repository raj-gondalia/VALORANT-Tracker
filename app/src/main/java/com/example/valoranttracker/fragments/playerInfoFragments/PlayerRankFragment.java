package com.example.valoranttracker.fragments.playerInfoFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.valoranttracker.R;
import com.example.valoranttracker.net.ValorantAPI;
import com.example.valoranttracker.net.Rank.Rank;
import com.example.valoranttracker.sharedPreferences.SharedPref;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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

        progressBar.setVisibility(View.VISIBLE);
        seekBar.setVisibility(View.GONE);
        eloTextView.setVisibility(View.GONE);
        rankImageView.setVisibility(View.GONE);
        rankingInTierTextView.setVisibility(View.GONE);
        rankTextView.setVisibility(View.GONE);
        rankRatingText.setVisibility(View.GONE);

        Bundle bundle = getActivity().getIntent().getExtras();
        String gameName = bundle.getString("gameName");
        String tag = bundle.getString("tag");

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String region = SharedPref.getInstance(getContext()).getRegion();

        Log.d(TAG, "onCreateView: " + region + " " + gameName + " " + tag);
        ValorantAPI valorantAPI = retrofit.create(ValorantAPI.class);
        valorantAPI.getRankData(region, gameName, tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Rank>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onNext(Rank rank) {
                        progressBar.setVisibility(GONE);
                        seekBar.setVisibility(View.VISIBLE);
                        eloTextView.setVisibility(View.VISIBLE);
                        rankImageView.setVisibility(View.VISIBLE);
                        rankingInTierTextView.setVisibility(View.VISIBLE);
                        rankTextView.setVisibility(View.VISIBLE);
                        rankRatingText.setVisibility(View.VISIBLE);

                        rankRatingText.setText("Rank Rating");
                        seekBar.setProgress(rank.getData().getRankingInTier());
                        eloTextView.setText("Elo - " + rank.getData().getElo());
                        rankTextView.setText(rank.getData().getCurrentTierPatched());
                        rankingInTierTextView.setText(rank.getData().getRankingInTier() + "/100");

                        String tier = rank.getData().getCurrentTierPatched().toLowerCase();
                        tier = tier.replace(" ","");

//                        Log.d(TAG, "onResponse: " + tier);

                        rankImageView.setImageResource(getResourceId(tier, "drawable", getActivity().getPackageName()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(GONE);
//                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return view;
    }

    private int getResourceId(String variableName, String resourceName, String packageName) {
        Log.d(TAG, "getResourceId: " + variableName + " " + getResources().getIdentifier(variableName, resourceName, packageName));
        return getResources().getIdentifier(variableName, resourceName, packageName);

    }
}
