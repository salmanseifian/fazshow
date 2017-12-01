package com.owjmedia.faaz.news;

import com.owjmedia.faaz.news.model.NewsResponse;
import com.owjmedia.faaz.general.networking.ApiClient;
import com.owjmedia.faaz.general.networking.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by salman on 11/11/17.
 */

public class NewsPresenter implements NewsContract.Presenter {

    public NewsPresenter(NewsContract.View newsView) {
        this.mNewsView = newsView;
    }


    @Override
    public void getNews(String token) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<NewsResponse> call = apiService.getNews(token);

        mNewsView.setLoadingIndicator(true);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                mNewsView.setLoadingIndicator(false);
                mNewsView.showNews(response.body().getResults());
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                mNewsView.setLoadingIndicator(false);
                mNewsView.showMessage(t.getLocalizedMessage());
            }
        });

    }

    NewsContract.View mNewsView;
}
