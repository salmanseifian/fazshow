package com.owjmedia.faaz.news;

import com.owjmedia.faaz.news.model.Result;
import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;

import java.util.List;

/**
 * Created by salman on 11/11/17.
 */

public interface NewsContract {

    interface View extends BaseView<Presenter> {

        void showNews(List<Result> news);
    }

    interface Presenter extends BasePresenter {

        void getNews(String token);
    }
}
