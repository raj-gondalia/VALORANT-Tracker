package com.example.valoranttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.example.valoranttracker.adapters.SectionPageAdapter;
import com.example.valoranttracker.fragments.homeScreenFragments.HistoryFragment;
import com.example.valoranttracker.fragments.homeScreenFragments.HomeFragment;
import com.example.valoranttracker.fragments.homeScreenFragments.LeaderboardFragment;
import com.example.valoranttracker.models.RealmModel;
import com.google.android.material.tabs.TabLayout;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;

    private static final String appID = "007";

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
        mSectionPageAdapter.addFragment(new HistoryFragment(), "History");
        mSectionPageAdapter.addFragment(new LeaderboardFragment(), "Leaderboard");
        mViewPager.setAdapter(mSectionPageAdapter);
    }

}