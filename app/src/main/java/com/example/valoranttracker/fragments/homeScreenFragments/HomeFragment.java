package com.example.valoranttracker.fragments.homeScreenFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.valoranttracker.R;
import com.example.valoranttracker.Stats;
import com.google.android.material.button.MaterialButton;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private EditText etUserName, etTag;
    private MaterialButton btnSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);

        etUserName = view.findViewById(R.id.homeFragUserNameEditText);
        etTag = view.findViewById(R.id.homeFragTagEditText);

        btnSearch = view.findViewById(R.id.homeFragGoButton);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Stats.class);
                intent.putExtra("username", etUserName.getText().toString().trim());
                intent.putExtra("tag", etTag.getText().toString().trim());
                startActivity(intent);
            }
        });

        return(view);
    }
}
