package com.example.valoranttracker.fragments.homeScreenFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.valoranttracker.R;

public class HistoryFragment extends Fragment {

    private Button btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.history_fragment, container, false);

        btn = view.findViewById(R.id.btnHistory);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "History Btn Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        return(view);
    }
}
