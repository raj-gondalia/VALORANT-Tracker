package com.example.valoranttracker.fragments.homeScreenFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.valoranttracker.R;
import com.example.valoranttracker.Stats;
import com.google.android.material.button.MaterialButton;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private EditText gameName, tag;
    private MaterialButton btnSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);

        gameName = view.findViewById(R.id.homeFragUserNameEditText);
        tag = view.findViewById(R.id.homeFragTagEditText);

        btnSearch = view.findViewById(R.id.homeFragGoButton);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(gameName.getText().toString().trim() != null && tag.getText().toString().trim() != null) {
                    Intent intent = new Intent(getActivity(), Stats.class);
                    intent.putExtra("gameName", gameName.getText().toString().trim());
                    intent.putExtra("tag", tag.getText().toString().trim());
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Please enter name and tag!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return(view);
    }
}
