package com.owjmedia.faaz.newsdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.data.NewsDetailResponse;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefacedTextView;
import com.owjmedia.faaz.general.utils.ImageHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by seifian on 11/12/17.
 */

public class NewsDetailFragment extends Fragment implements NewsDetailContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_detail_frg, container, false);
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
        txtNewsContent.setText(newsDetailResponse.getContent());
        ImageHelper.getInstance(getContext()).imageLoader(newsDetailResponse.getFeaturedImage(), imgNews, ImageHelper.ImageType.NEWS);
    }

    @Override
    public void showLikedSuccessfully() {

    }

    private String getNewsId() {
        return getArguments().getString(Constants.KEYS.NEWS_ID);
    }

    private NewsDetailContract.Presenter mNewsDetailPresenter;

    @BindView(R.id.imgNews)
    ImageView imgNews;

    @BindView(R.id.txtNewsTitle)
    TypefacedTextView txtNewsTitle;

    @BindView(R.id.txtNewsContent)
    TypefacedTextView txtNewsContent;

}
