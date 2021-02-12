package com.example.valoranttracker.fragments.homeScreenFragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.valoranttracker.database.DBHelper;
import com.example.valoranttracker.R;
import com.example.valoranttracker.activities.Stats;
import com.example.valoranttracker.net.Profile.Profile;
import com.example.valoranttracker.net.ValorantAPI;
import com.example.valoranttracker.net.Puuid.Puuid;
import com.example.valoranttracker.sharedPreferences.SharedPref;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.view.View.GONE;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    public static final String BASE_URL = "https://api.henrikdev.xyz";

    private EditText gameName, tag;
    private ProgressBar progressBar;
    private MaterialButton btnSearch;

    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);

        gameName = view.findViewById(R.id.homeFragUserNameEditText);
        tag = view.findViewById(R.id.homeFragTagEditText);

        progressBar = view.findViewById(R.id.homeFragProgressBar);

        spinner = view.findViewById(R.id.homeFragSpinner);

        List<String> regions = new ArrayList<String>();
        regions.add("ap");
        regions.add("na");
        regions.add("eu");
        regions.add("kr");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, regions);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String region = (String) adapterView.getItemAtPosition(i);

                SharedPref sharedPref = SharedPref.getInstance(getContext());
                sharedPref.addRegion(region);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnSearch = view.findViewById(R.id.homeFragGoButton);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);

                if(gameName.getText().toString().trim() != null && tag.getText().toString().trim() != null) {

                    getPuuid(gameName.getText().toString().trim(), tag.getText().toString().trim());


                } else {

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Please enter name and tag!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return(view);
    }

    private void getPuuid(String gameNameVal, String tagVal) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.d(TAG, "getPuuid: " + gameNameVal + " " + tagVal);

        ValorantAPI valorantAPI = retrofit.create(ValorantAPI.class);
        valorantAPI.getProfileData(gameNameVal, tagVal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Profile>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Profile profile) {
                        if(!profile.getStatus().equals("200")) {
                            progressBar.setVisibility(GONE);

                            Toast.makeText(getContext(), profile.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {


                            DBHelper dbHelper = new DBHelper(getContext());

                            String region = SharedPref.getInstance(getContext()).getRegion();

                            dbHelper.insertHistory(gameNameVal, tagVal, region);

                            progressBar.setVisibility(GONE);

                            Intent intent = new Intent(getActivity(), Stats.class);
                            intent.putExtra("gameName", gameNameVal);
                            intent.putExtra("tag", tagVal);
                            startActivity(intent);

                            getActivity().finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(GONE);
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(), "Something went wrong! Maybe the account doesn't exist or is private. Activate it by logging into tracer.gg.", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}
