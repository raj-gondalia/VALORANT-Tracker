package com.example.valoranttracker.fragments.playerInfoFragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.valoranttracker.activities.MainActivity;
import com.example.valoranttracker.R;
import com.example.valoranttracker.net.ValorantAPI;
import com.example.valoranttracker.net.Profile.Profile;
import com.squareup.picasso.Picasso;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayerOverviewFragments extends Fragment {

    private static final String TAG = "PlayerOverviewFragments";
    public static final String BASE_URL = "https://api.henrikdev.xyz";

    private ProgressBar progressBar;
    private LinearLayout linearLayout1, linearLayout2;

    private TextView gameNameTextView, tagTextView, playTimeTextView, matchesTextView, winsTextView, winPercenTextView, killsTextView, assistsTextView, flawlessTextView,
                            deathsText, kdRatioTextView, headshotsTextView, headshotpercenTextView, firstbloodsTextView, acesTextView, clutchesTextView;
    private ImageView playerCardImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_overview_fragment, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gameNameTextView = view.findViewById(R.id.playerOverviewFragGameNameTextView);
        acesTextView = view.findViewById(R.id.playerOverviewFragAcesTextView);
        clutchesTextView = view.findViewById(R.id.playerOverviewFragClutchesTextView);
        assistsTextView = view.findViewById(R.id.playerOverviewFragAssitsTextView);
        killsTextView = view.findViewById(R.id.playerOverviewFragKillsTextView);
        deathsText = view.findViewById(R.id.playerOverviewFragDeathsTextView);
        firstbloodsTextView = view.findViewById(R.id.playerOverviewFragFirstBloodsTextView);
        flawlessTextView = view.findViewById(R.id.playerOverviewFragFlawlessTextView);
        matchesTextView = view.findViewById(R.id.playerOverviewFragGamesPlayedTextView);
        headshotpercenTextView = view.findViewById(R.id.playerOverviewFragHeadshotPercentageTextView);
        headshotsTextView = view.findViewById(R.id.playerOverviewFragHeadshotsTextView);
        kdRatioTextView = view.findViewById(R.id.playerOverviewFragKDRatioTextView);
        playerCardImageView = view.findViewById(R.id.playerOverviewFragPlayerCardImageView);
        playTimeTextView = view.findViewById(R.id.playerOverviewFragTimePLayedTextView);
        tagTextView = view.findViewById(R.id.playerOverviewFragTagTextView);
        winPercenTextView = view.findViewById(R.id.playerOverviewFragWinPercentageTextView);
        winsTextView = view.findViewById(R.id.playerOverviewFragWinsTextView);

        progressBar = view.findViewById(R.id.playerOverviewFragProgressBar);

        linearLayout1 = view.findViewById(R.id.playerOverviewFragLinearLayout1);
        linearLayout2 = view.findViewById(R.id.playerOverviewFragLinearLayout2);

        linearLayout1.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.VISIBLE);


        Bundle bundle = getActivity().getIntent().getExtras();
        String gameName = bundle.getString("gameName");
        String tag = bundle.getString("tag");

        ValorantAPI valorantAPI = retrofit.create(ValorantAPI.class);
        valorantAPI.getProfileData(gameName,tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Profile>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onNext(Profile profile) {
                        if(!profile.getStatus().equals("200")) {

                            Log.d(TAG, "onNext: " + profile.getStatus() + " mess: " + profile.getMessage());

                            Toast.makeText(getActivity(), profile.getMessage(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }


                        linearLayout1.setVisibility(View.VISIBLE);
                        linearLayout2.setVisibility(View.GONE);

                        Picasso.get()
                                .load(profile.getStats().getPlayercard())
                                .into(playerCardImageView);

                        playTimeTextView.setText(profile.getStats().getPlaytime().getPlaytimepatched());
                        matchesTextView.setText("Played - " + profile.getStats().getMatches());
                        killsTextView.setText("Kills - " + profile.getStats().getKills());
                        deathsText.setText("Deaths - " + profile.getStats().getDeaths());
                        assistsTextView.setText("Assits - " + profile.getStats().getAssists());
                        kdRatioTextView.setText("KD Ratio - " + profile.getStats().getKdratio());
                        headshotsTextView.setText("Headshots - " + profile.getStats().getHeadshots());
                        headshotpercenTextView.setText("Headshot Percentage - " + profile.getStats().getHeadshotpercentage());
                        winsTextView.setText("Wins - " + profile.getStats().getWins());
                        winPercenTextView.setText("Win Percentage - " + profile.getStats().getWinpercentage());
                        firstbloodsTextView.setText("Firstbloods - " + profile.getStats().getFirstbloods());
                        acesTextView.setText("Aces - " + profile.getStats().getAces());
                        clutchesTextView.setText("Clutches - " + profile.getStats().getClutches());
                        flawlessTextView.setText("Flawless - " + profile.getStats().getFlawless());
                        gameNameTextView.setText(gameName);
                        tagTextView.setText(tag);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onFailure: " + e.getLocalizedMessage());
                        Toast.makeText(getActivity(), "Something went wrong! Maybe the account is private. Activate it by logging into tracer.gg.", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return view;
    }



}
