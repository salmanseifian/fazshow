package com.owjmedia.faaz.newsdetail;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.owjmedia.faaz.R;
import com.owjmedia.faaz.data.NewsDetailResponse;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.AppManager;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefacedTextView;
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
        setContentView(R.layout.news_detail_frg);
        ButterKnife.bind(this);

        mNewsDetailPresenter = new NewsDetailPresenter(this);
        mNewsDetailPresenter.getNewsDetail(getNewsId());
    }


    @Override
    public void setPresenter(NewsDetailContract.Presenter presenter) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showNewsDetail(NewsDetailResponse newsDetailResponse) {
        txtNewsTitle.setText(newsDetailResponse.getTitle());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtNewsContent.setText(Html.fromHtml(newsDetailResponse.getContent(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            txtNewsContent.setText(Html.fromHtml(newsDetailResponse.getContent()));
        }
        ImageHelper.getInstance(this).imageLoader(newsDetailResponse.getImage(), imgNews, ImageHelper.ImageType.NEWS);
    }

    @Override
    public void showLikedSuccessfully() {

    }

    private String getNewsId() {
        return getIntent().getStringExtra(Constants.KEYS.NEWS_ID);
    }

    @OnClick(R.id.lottieLike)
    public void like() {
        lottieLike.playAnimation();
        mNewsDetailPresenter.like(AppManager.getString(this, Constants.KEYS.TOKEN), getNewsId());
    }


    private NewsDetailContract.Presenter mNewsDetailPresenter;

    @BindView(R.id.imgNews)
    ImageView imgNews;

    @BindView(R.id.txtNewsTitle)
    TypefacedTextView txtNewsTitle;

    @BindView(R.id.txtNewsContent)
    TypefacedTextView txtNewsContent;

    @BindView(R.id.lottieLike)
    LottieAnimationView lottieLike;
}
