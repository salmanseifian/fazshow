package com.owjmedia.faaz.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.authenticate.AuthenticateActivity;
import com.owjmedia.faaz.data.Result;
import com.owjmedia.faaz.general.utils.ProgressDialog;
import com.owjmedia.faaz.vote.VotingActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by salman on 11/9/17.
 */

public class NewsActivity extends AppCompatActivity implements NewsContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_act);
        ButterKnife.bind(this);

        // Set up the toolbar.
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        // Set up the navigation drawer.
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        mNewsPresenter = new NewsPresenter(this);
        mProgressDialog = new ProgressDialog(this);

        // Set up recycler view
        initView();

    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mNewsAdapter = new NewsAdapter(mNews, new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Result newsItem) {

            }
        });
        mRecyclerView.setAdapter(mNewsAdapter);
        mNewsPresenter.getNews();
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active)
            mProgressDialog.show();
        else
            mProgressDialog.cancel();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showNews(List<Result> news) {
        mNewsAdapter.update(news);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile:
                        Intent profileIntent = new Intent(NewsActivity.this, AuthenticateActivity.class);
                        startActivity(profileIntent);
                        break;
                    case R.id.voting:
                        Intent votingIntent = new Intent(NewsActivity.this, VotingActivity.class);
                        startActivity(votingIntent);
                        break;
                    default:
                        break;
                }
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

    }

    private NewsContract.Presenter mNewsPresenter;
    private NewsAdapter mNewsAdapter;
    private List<Result> mNews;

    ProgressDialog mProgressDialog;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

}
