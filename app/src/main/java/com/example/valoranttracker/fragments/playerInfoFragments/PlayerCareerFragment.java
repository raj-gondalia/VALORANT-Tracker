package com.example.valoranttracker.fragments.playerInfoFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttracker.adapters.CareerAdapter;
import com.example.valoranttracker.activities.MainActivity;
import com.example.valoranttracker.R;
import com.example.valoranttracker.net.ValorantAPI;
import com.example.valoranttracker.models.CareerModel;
import com.example.valoranttracker.net.Matches.Matches;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.view.View.GONE;

public class PlayerCareerFragment extends Fragment {

    public static final String TAG = "PlayerCareerFragment";
    public static final String BASE_URL = "https://api.henrikdev.xyz";

    private RecyclerView recyclerView;
    private CareerAdapter adapter;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_career_fragment, container, false);

        recyclerView = view.findViewById(R.id.playerCareerFragRecyclerView);
        progressBar = view.findViewById(R.id.playerCareerFragProgressBar);
        linearLayout = view.findViewById(R.id.playerCareerFragLinearLayout);

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(GONE);
        linearLayout.setVisibility(GONE);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Bundle bundle = getActivity().getIntent().getExtras();
        String gameName = bundle.getString("gameName");
        String tag = bundle.getString("tag");

        ValorantAPI valorantAPI = retrofit.create(ValorantAPI.class);
        valorantAPI.getMatchesData(gameName,tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Matches>() {
                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(View.GONE);

//                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Matches matches) {
                        if(!matches.getStatus().equals("200")) {
                            Toast.makeText(getActivity(), matches.getMessage(), Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(GONE);

                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }

                        ArrayList<CareerModel> arrayList = new ArrayList<>();
                        for(int i =0; i < matches.getMatches().size(); i++) {
                            if(!matches.getMatches().get(i).isAvailable()) {
                                continue;
                            }

                            if(!matches.getMatches().get(i).getMetadata().getModename().equals("Competitive")) {
                                continue;
                            }

                            CareerModel careerModel = new CareerModel();
                            careerModel.setAgent(getResourceId(matches.getMatches().get(i).getMetadata().getAgentplayed().toLowerCase(),
                                    "drawable", getActivity().getPackageName()));
                            careerModel.setMap(matches.getMatches().get(i).getMetadata().getMap());
                            careerModel.setModeSrc(getResourceId(matches.getMatches().get(i).getMetadata().getModename().toLowerCase(),
                                    "drawable", getActivity().getPackageName()));
                            careerModel.setMode(matches.getMatches().get(i).getMetadata().getModename());
                            careerModel.setKda(matches.getMatches().get(i).getGame().getKda().getKda());
                            careerModel.setGameid(matches.getMatches().get(i).getMetadata().getGameid());
                            careerModel.setPlayerhaswon(matches.getMatches().get(i).getMetadata().isPlayerhaswon());
                            careerModel.setRoundslost(matches.getMatches().get(i).getGame().getRoundslost());
                            careerModel.setRoundswon(matches.getMatches().get(i).getGame().getRoundswon());
                            careerModel.setTimestamp(matches.getMatches().get(i).getMetadata().getTimestamp());

                            arrayList.add(careerModel);
                        }


                        progressBar.setVisibility(GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        linearLayout.setVisibility(View.VISIBLE);

                        adapter = new CareerAdapter(getContext(), arrayList);

                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setHasFixedSize(true);
                    }
                });


        return view;
    }

    private int getResourceId(String variableName, String resourceName, String packageName) {
//        Log.d(TAG, "getResourceId: " + variableName + " " + getResources().getIdentifier(variableName, resourceName, packageName));
        return getResources().getIdentifier(variableName, resourceName, packageName);

    }
}
