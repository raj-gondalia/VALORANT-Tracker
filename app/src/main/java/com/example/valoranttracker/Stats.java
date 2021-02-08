package com.example.valoranttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.valoranttracker.adapters.SectionPageAdapter;
import com.example.valoranttracker.fragments.playerInfoFragments.PlayerCareerFragment;
import com.example.valoranttracker.fragments.playerInfoFragments.PlayerOverviewFragments;
import com.example.valoranttracker.fragments.playerInfoFragments.PlayerRankFragment;
import com.google.android.material.tabs.TabLayout;

public class Stats extends AppCompatActivity {

    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Bundle bundle = getIntent().getExtras();

        mViewPager = findViewById(R.id.statsViewPager);
        setupViewPager();

        Toolbar toolbar = findViewById(R.id.statsToolBar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Stats.this, MainActivity.class);
                startActivity(intent);
            }
        });
        toolbar.setTitle(String.format("%s#%s", bundle.getString("username"), bundle.get("tag").toString()));

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