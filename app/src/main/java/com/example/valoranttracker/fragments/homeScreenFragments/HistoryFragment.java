package com.example.valoranttracker.fragments.homeScreenFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttracker.database.DBHelper;
import com.example.valoranttracker.R;
import com.example.valoranttracker.adapters.HistoryAdapter;
import com.example.valoranttracker.models.HistoryModel;

import java.util.ArrayList;
import java.util.List;

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

        DBHelper dbHelper = new DBHelper(getContext());

        List<HistoryModel> historyModels = dbHelper.getHistory();

        HistoryAdapter adapter = new HistoryAdapter(getContext(), (ArrayList<HistoryModel>) historyModels);

        progressBar.setVisibility(View.GONE);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return(view);
    }
}
