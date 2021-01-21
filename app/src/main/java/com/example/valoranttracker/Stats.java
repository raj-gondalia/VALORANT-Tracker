package com.example.valoranttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.valoranttracker.Adapters.SectionPageAdapter;
import com.example.valoranttracker.playerInfoFragments.PlayerAgents;
import com.example.valoranttracker.playerInfoFragments.PlayerCareer;
import com.example.valoranttracker.playerInfoFragments.PlayerMaps;
import com.example.valoranttracker.playerInfoFragments.PlayerOverview;
import com.example.valoranttracker.playerInfoFragments.PlayerWeapons;
import com.google.android.material.tabs.TabLayout;

public class Stats extends AppCompatActivity {

    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        mViewPager = findViewById(R.id.statsViewPager);
        setupViewPager();

        Toolbar toolbar = findViewById(R.id.statsToolBar);

        TabLayout tabLayout = findViewById(R.id.statsTabLayout);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager() {
        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mSectionPageAdapter.addFragment(new PlayerOverview(), "Overview");
        mSectionPageAdapter.addFragment(new PlayerCareer(), "Career");
        mSectionPageAdapter.addFragment(new PlayerAgents(), "Agents");
        mSectionPageAdapter.addFragment(new PlayerMaps(), "Maps");
        mSectionPageAdapter.addFragment(new PlayerWeapons(), "Weapons");
        mViewPager.setAdapter(mSectionPageAdapter);
    }
}