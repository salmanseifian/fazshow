package com.owjmedia.faaz.vote;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefacedTabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by salman on 11/9/17.
 */

public class VotingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voting_act);
        ButterKnife.bind(this);

        // Set up the toolbar.
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set up view pager
        setupViewPager();
        tabs.setupWithViewPager(viewPager);

    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new BoysFragment(), getString(R.string.boys));
        adapter.addFragment(new GirlsFragment(), getString(R.string.girls));
        viewPager.setAdapter(adapter);
    }


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TypefacedTabLayout tabs;

    @BindView(R.id.viewpager)
    ViewPager viewPager;
}
