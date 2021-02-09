package com.example.valoranttracker.fragments.homeScreenFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttracker.R;
import com.example.valoranttracker.models.RealmModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class HistoryFragment extends Fragment {

    private static final String TAG = "HistoryFragment";

    private ProgressBar progressBar;

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.history_fragment, container, false);

        progressBar = view.findViewById(R.id.historyFragProgressBar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = view.findViewById(R.id.historyFragRecyclerView);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<RealmModel> realmModels = realm.where(RealmModel.class).findAll();

                ArrayList<RealmModel> arrayList = new ArrayList<>();

                Log.d(TAG, "execute: " + realmModels.toString());

                progressBar.setVisibility(View.GONE);

                for(RealmModel realmModel : realmModels) {
                    arrayList.add(realmModel);
                }


            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

                Log.d(TAG, "onSuccess: Successfully read");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d(TAG, "onError: " + error.getMessage());
            }
        });

        return(view);
    }
}
