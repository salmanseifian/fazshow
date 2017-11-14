package com.owjmedia.faaz.news;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.authenticate.AuthenticateActivity;
import com.owjmedia.faaz.data.Result;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ProgressDialog;
import com.owjmedia.faaz.newsdetail.NewsDetailActivity;
import com.owjmedia.faaz.vote.VotingActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class NewsActivity extends AppCompatActivity implements NewsContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_act);
        ButterKnife.bind(this);

        // Set up the toolbar.
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        mNewsPresenter = new NewsPresenter(this);
        mProgressDialog = new ProgressDialog(this);

        // init the bottom sheet behavior
        mBottomSheetBahavior = BottomSheetBehavior.from(mBottomSheet);

        // Set up recycler view
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mBottomSheetBahavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mNewsAdapter = new NewsAdapter(mNews, new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Result newsItem, ImageView imgNews) {
                Intent intent = new Intent(NewsActivity.this, NewsDetailActivity.class);
                String transitionName = getString(R.string.news_image);

                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(NewsActivity.this, imgNews, transitionName);
                intent.putExtra(Constants.KEYS.NEWS_ID, String.valueOf(newsItem.getId()));
                startActivity(intent, transitionActivityOptions.toBundle());
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
        mRecyclerView.scheduleLayoutAnimation();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mBottomSheetBahavior.getState() != BottomSheetBehavior.STATE_COLLAPSED)
                    mBottomSheetBahavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                else
                    mBottomSheetBahavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.rl_header)
    public void hideBottomSheet() {
        mBottomSheetBahavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @OnClick(R.id.rl_profile)
    public void goToProfile() {
        Intent profileIntent = new Intent(NewsActivity.this, AuthenticateActivity.class);
        startActivity(profileIntent);
    }

    @OnClick(R.id.rl_vote)
    public void goToVote() {
        Intent votingIntent = new Intent(NewsActivity.this, VotingActivity.class);
        startActivity(votingIntent);
    }


    private NewsContract.Presenter mNewsPresenter;
    private NewsAdapter mNewsAdapter;
    private List<Result> mNews;

    ProgressDialog mProgressDialog;
    BottomSheetBehavior mBottomSheetBahavior;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.bottom_sheet)
    View mBottomSheet;

}
