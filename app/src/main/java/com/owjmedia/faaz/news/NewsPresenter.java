package com.owjmedia.faaz.news;

import android.support.annotation.NonNull;

import com.owjmedia.faaz.general.networking.Injector;
import com.owjmedia.faaz.news.model.NewsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by salman on 11/11/17.
 */

class NewsPresenter implements NewsContract.Presenter {

    NewsPresenter(NewsContract.View newsView) {
        this.mNewsView = newsView;
    }


    @Override
    public void getNews() {
        Call<NewsResponse> call = Injector.provideApiService().getNews();

        mNewsView.setLoadingIndicator(true);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewsResponse> call, @NonNull Response<NewsResponse> response) {
                mNewsView.setLoadingIndicator(false);
                mNewsView.showNews(response.body().getResults());
            }

            @Override
            public void onFailure(@NonNull Call<NewsResponse> call, @NonNull Throwable t) {
                mNewsView.setLoadingIndicator(false);
                mNewsView.showConnectionError();
            }
        });

    }

    private NewsContract.View mNewsView;
}
