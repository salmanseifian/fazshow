package com.owjmedia.faaz.newsdetail;

import com.owjmedia.faaz.newsdetail.model.NewsDetailResponse;
import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;

/**
 * Created by seifian on 11/12/17.
 */

public interface NewsDetailContract {

    interface View extends BaseView<Presenter>{

        void showNewsDetail(NewsDetailResponse newsDetailResponse);

        void showLikedSuccessfully();
    }

    interface Presenter extends BasePresenter {

        void getNewsDetail(String newsId);

        void like(String accessToken, String newsId);
    }
}
