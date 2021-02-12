package com.example.valoranttracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Spinner;

import com.example.valoranttracker.R;
import com.example.valoranttracker.adapters.SectionPageAdapter;
import com.example.valoranttracker.fragments.homeScreenFragments.HistoryFragment;
import com.example.valoranttracker.fragments.homeScreenFragments.HomeFragment;
import com.example.valoranttracker.fragments.homeScreenFragments.LeaderboardFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {



    public static final String TAG = "MainActivity";
    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.container);
        setupViewPager();

        mTabLayout = findViewById(R.id.activityMainTabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        mToolbar = findViewById(R.id.activityMainToolbar);
        mToolbar.setTitle("Valorant Tracker");



    }

    private void setupViewPager() {
        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mSectionPageAdapter.addFragment(new HomeFragment(), "Home");
        mSectionPageAdapter.addFragment(new LeaderboardFragment(), "Leaderboard");
        mSectionPageAdapter.addFragment(new HistoryFragment(), "History");
        mViewPager.setAdapter(mSectionPageAdapter);
    }

}