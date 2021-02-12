package com.example.valoranttracker.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttracker.R;
import com.example.valoranttracker.adapters.MatchDialogAdapter;
import com.example.valoranttracker.models.MatchDetailModel;
import com.example.valoranttracker.net.Match.Match;
import com.example.valoranttracker.net.ValorantAPI;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.valoranttracker.net.ValorantAPI.BASE_URL;

public class MatchDialog extends Dialog {

    private static final String TAG = "MatchDialog";

    private Context context;

    private String gameId;

    private RecyclerView recyclerView;
    private LinearLayout linearLayout;
    private ProgressBar progressBar;

    public MatchDialog(@NonNull Context context, String gameId) {
        super(context);

        this.context = context;
        this.gameId = gameId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.match_dialog);

        recyclerView = findViewById(R.id.matchDialogRecyclerView);
        linearLayout = findViewById(R.id.matchDialogLinearLayout);
        progressBar = findViewById(R.id.matchDialogProgressBar);

        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ValorantAPI valorantAPI = retrofit.create(ValorantAPI.class);

        valorantAPI.getMatchData(gameId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Match>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Match match) {


                        ArrayList<MatchDetailModel> arrayList = new ArrayList<>();

                        for(int i = 0; i < match.getData().getPlayer().getByteam().getBlueTeam().size(); i++) {
                            MatchDetailModel matchDetailModel = new MatchDetailModel();

                            int len = match.getData().getPlayer().getByteam().getBlueTeam().get(i).getGameName().length();

                            matchDetailModel.setGameName(match.getData().getPlayer().getByteam().getBlueTeam().get(i).getGameName().substring(0, len-5));
                            matchDetailModel.setKda(match.getData().getPlayer().getByteam().getBlueTeam().get(i).getKda().getKda());
                            matchDetailModel.setTagLine(match.getData().getPlayer().getByteam().getBlueTeam().get(i).getGameName().substring(len-4, len));
                            matchDetailModel.setTeam("Blue");
                            matchDetailModel.setAgentPlayed(getResourceId(match.getData().getPlayer().getByteam().getBlueTeam().get(i).getAgent().toLowerCase(),
                                     "drawable", context.getPackageName()));

                            String tier = match.getData().getPlayer().getByteam().getBlueTeam().get(i).getTier().toLowerCase();
                            tier = tier.replace(" ","");

                            Log.d(TAG, "onNext: " + tier);

                            matchDetailModel.setTier(getResourceId(tier, "drawable", context.getPackageName()));

                            arrayList.add(matchDetailModel);
                        }

                        for(int i = 0; i < match.getData().getPlayer().getByteam().getRedTeam().size(); i++) {
                            MatchDetailModel matchDetailModel = new MatchDetailModel();

                            int len = match.getData().getPlayer().getByteam().getRedTeam().get(i).getGameName().length();

                            matchDetailModel.setGameName(match.getData().getPlayer().getByteam().getRedTeam().get(i).getGameName().substring(0, len-5));
                            matchDetailModel.setKda(match.getData().getPlayer().getByteam().getRedTeam().get(i).getKda().getKda());
                            matchDetailModel.setTagLine(match.getData().getPlayer().getByteam().getRedTeam().get(i).getGameName().substring(len-4, len));
                            matchDetailModel.setTeam("Red");
                            matchDetailModel.setAgentPlayed(getResourceId(match.getData().getPlayer().getByteam().getRedTeam().get(i).getAgent().toLowerCase(),
                                    "drawable", context.getPackageName()));

                            String tier = match.getData().getPlayer().getByteam().getRedTeam().get(i).getTier().toLowerCase();
                            tier = tier.replace(" ","");

                            Log.d(TAG, "onNext: " + tier);

                            matchDetailModel.setTier(getResourceId(tier, "drawable", context.getPackageName()));

                            arrayList.add(matchDetailModel);
                        }

                        MatchDialogAdapter adapter = new MatchDialogAdapter(context, arrayList);

                        recyclerView.setAdapter(adapter);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));

                        recyclerView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        linearLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private int getResourceId(String variableName, String resourceName, String packageName) {
//        Log.d(TAG, "getResourceId: " + variableName + " " + getResources().getIdentifier(variableName, resourceName, packageName));
        return context.getResources().getIdentifier(variableName, resourceName, packageName);

    }
}
