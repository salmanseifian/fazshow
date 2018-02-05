package com.owjmedia.faaz.newsdetail;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.AuthenticationDialog;
import com.owjmedia.faaz.newsdetail.model.NewsDetailResponse;
import com.owjmedia.faaz.general.utils.AppManager;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefaceTextView;
import com.owjmedia.faaz.general.utils.ImageHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by seifian on 11/13/17.
 */

public class NewsDetailActivity extends AppCompatActivity implements NewsDetailContract.View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail_act);
        ButterKnife.bind(this);

        supportPostponeEnterTransition();

        // Set up the toolbar.
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
            ab.setDisplayHomeAsUpEnabled(true);
        }
        mNewsDetailPresenter = new NewsDetailPresenter(this);
        mNewsDetailPresenter.getNewsDetail(getNewsId());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showConnectionError() {

    }

    @Override
    public void showNewsDetail(NewsDetailResponse newsDetailResponse) {

        mNewsDetailResponse = newsDetailResponse;
        supportStartPostponedEnterTransition();

        txtNewsTitle.setText(newsDetailResponse.getTitle());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtNewsContent.setText(Html.fromHtml(newsDetailResponse.getContent(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            txtNewsContent.setText(Html.fromHtml(newsDetailResponse.getContent()));
        }
        txtNewsContent.setMovementMethod(LinkMovementMethod.getInstance());

        if (newsDetailResponse.isLiked()) {
            lottieLike.playAnimation();
        }
        ImageHelper.getInstance(this).imageLoader(newsDetailResponse.getImage(), imgNews, ImageHelper.ImageType.NEWS_DETAIL);
    }

    @Override
    public void showLikedSuccessfully() {

    }

    private String getNewsId() {
        return getIntent().getStringExtra(Constants.KEYS.NEWS_ID);
    }

    @OnClick(R.id.lottieLike)
    public void like() {
        if (AppManager.isLogin(this) && mNewsDetailResponse != null) {
            if (mNewsDetailResponse.isLiked()) {
                lottieLike.reverseAnimation();
                mNewsDetailResponse.setLiked(false);
            } else {
                lottieLike.playAnimation();
                mNewsDetailResponse.setLiked(true);
            }
            mNewsDetailPresenter.like(getNewsId());
        } else {
            new AuthenticationDialog().show(getSupportFragmentManager(), null);
        }

    }


    private NewsDetailContract.Presenter mNewsDetailPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.imgNews)
    ImageView imgNews;

    @BindView(R.id.txtNewsTitle)
    TypefaceTextView txtNewsTitle;

    @BindView(R.id.txtNewsContent)
    TypefaceTextView txtNewsContent;

    @BindView(R.id.lottieLike)
    LottieAnimationView lottieLike;

    private NewsDetailResponse mNewsDetailResponse;
}
