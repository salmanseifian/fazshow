package com.owjmedia.faaz.news;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;
import com.owjmedia.faaz.R;
import com.owjmedia.faaz.about.AboutActivity;
import com.owjmedia.faaz.authenticate.AuthenticateActivity;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.news.model.Result;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.AppManager;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefacedTextView;
import com.owjmedia.faaz.general.utils.ProgressDialog;
import com.owjmedia.faaz.newsdetail.NewsDetailActivity;
import com.owjmedia.faaz.vote.VoteActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;


public class NewsActivity extends AppCompatActivity implements NewsContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
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
        setupBottomSheet();

        // Set up recycler view
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mBottomSheetBahavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        setupBottomSheet();
    }

    private void setupBottomSheet() {
        if (!AppManager.isLogin(this)) {
            mRlExit.setVisibility(View.INVISIBLE);
            mTxtProfile.setText(getString(R.string.login_and_register));
        } else {
            mRlExit.setVisibility(View.VISIBLE);
            mTxtProfile.setText(getString(R.string.profile));
        }
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mNewsAdapter = new NewsAdapter(mNews, new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Result newsItem, ImageView imgNews, TypefacedTextView txtNews) {
                Intent intent = new Intent(NewsActivity.this, NewsDetailActivity.class);
                intent.putExtra(Constants.KEYS.NEWS_ID, String.valueOf(newsItem.getId()));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Pair<View, String> image = Pair.create((View) imgNews, getString(R.string.news_image));
                    Pair<View, String> title = Pair.create((View) txtNews, getString(R.string.news_title));
                    ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(NewsActivity.this, image, title);
                    startActivity(intent, transitionActivityOptions.toBundle());
                } else {
                    startActivity(intent);
                }
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
        ActivityUtils.showToast(this, getString(R.string.no_internet_connection), "emoji_shock.json");
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
        Intent votingIntent = new Intent(NewsActivity.this, VoteActivity.class);
        startActivity(votingIntent);
    }

    @OnClick(R.id.rl_competition)
    public void goToCompetition() {
        ActivityUtils.showToast(this, getString(R.string.soon), "emoji_wink.json");
    }

    @OnClick(R.id.rl_club)
    public void goToClub() {
        ActivityUtils.showToast(this, getString(R.string.soon), "emoji_wink.json");
    }

    @OnClick(R.id.rl_info)
    public void goToInfo() {
        Intent infoActivity = new Intent(NewsActivity.this, AboutActivity.class);
        startActivity(infoActivity);
    }

    @OnClick(R.id.rl_exit)
    public void logout() {
        AppManager.setString(this, Constants.KEYS.TOKEN, null);
        mBottomSheetBahavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        setupBottomSheet();
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

    @BindView(R.id.txt_profile)
    TypefacedTextView mTxtProfile;

    @BindView(R.id.rl_exit)
    ViewGroup mRlExit;

}
