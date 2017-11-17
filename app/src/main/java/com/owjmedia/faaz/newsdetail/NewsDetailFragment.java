package com.owjmedia.faaz.newsdetail;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by seifian on 11/12/17.
 */

public class NewsDetailFragment extends Fragment implements NewsDetailContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_detail_act_card, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

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
        ImageHelper.getInstance(getContext()).imageLoader(newsDetailResponse.getImage(), imgNews, ImageHelper.ImageType.NEWS);
    }

    @Override
    public void showLikedSuccessfully() {

    }

    private String getNewsId() {
        return getArguments().getString(Constants.KEYS.NEWS_ID);
    }

    @OnClick(R.id.lottieLike)
    public void like() {
        lottieLike.playAnimation();
        mNewsDetailPresenter.like(AppManager.getString(getContext(), Constants.KEYS.TOKEN), getNewsId());
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
