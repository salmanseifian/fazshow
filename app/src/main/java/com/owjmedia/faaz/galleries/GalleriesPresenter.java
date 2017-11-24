package com.owjmedia.faaz.galleries;

import android.util.Log;

import com.owjmedia.faaz.galleries.model.GalleriesResponse;
import com.owjmedia.faaz.general.networking.ApiClient;
import com.owjmedia.faaz.general.networking.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by salman on 11/23/17.
 */

public class GalleriesPresenter implements GalleriesContract.Presenter {

    public GalleriesPresenter(GalleriesContract.View galleryView) {
        mGalleryView = galleryView;
    }

    @Override
    public void start() {

    }

    @Override
    public void getImageGalleries() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<GalleriesResponse>> call = apiService.getImageGalleries();

        call.enqueue(new Callback<List<GalleriesResponse>>() {
            @Override
            public void onResponse(Call<List<GalleriesResponse>> call, Response<List<GalleriesResponse>> response) {
                if (response.code() == 200)
                    mGalleryView.showGalleries(response.body());
                else
                    mGalleryView.showMessage(response.message());
            }

            @Override
            public void onFailure(Call<List<GalleriesResponse>> call, Throwable t) {
                Log.d("LOG", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getVideoGalleries() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<GalleriesResponse>> call = apiService.getVideoGalleries();

        call.enqueue(new Callback<List<GalleriesResponse>>() {
            @Override
            public void onResponse(Call<List<GalleriesResponse>> call, Response<List<GalleriesResponse>> response) {
                if (response.code() == 200)
                    mGalleryView.showGalleries(response.body());
                else
                    mGalleryView.showMessage(response.message());
            }

            @Override
            public void onFailure(Call<List<GalleriesResponse>> call, Throwable t) {

            }
        });

    }

    GalleriesContract.View mGalleryView;
}
