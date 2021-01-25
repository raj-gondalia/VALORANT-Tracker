package com.example.valoranttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.valoranttracker.Adapters.SectionPageAdapter;
import com.example.valoranttracker.playerInfoFragments.PlayerCareerFragment;
import com.example.valoranttracker.playerInfoFragments.PlayerOverviewFragments;
import com.example.valoranttracker.playerInfoFragments.PlayerRankFragment;
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
        mSectionPageAdapter.addFragment(new PlayerOverviewFragments(), "Overview");
        mSectionPageAdapter.addFragment(new PlayerCareerFragment(), "Career");
        mSectionPageAdapter.addFragment(new PlayerRankFragment(), "Rank");
        mViewPager.setAdapter(mSectionPageAdapter);
    }
}