package com.owjmedia.faaz.galleries;

import android.util.Log;

import com.owjmedia.faaz.galleries.model.GalleriesResponse;
import com.owjmedia.faaz.general.networking.Injector;
import com.owjmedia.faaz.general.networking.ApiService;

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
    public void getImageGalleries() {
        Call<List<GalleriesResponse>> call = Injector.provideApiService().getImageGalleries();

        mGalleryView.setLoadingIndicator(true);
        call.enqueue(new Callback<List<GalleriesResponse>>() {
            @Override
            public void onResponse(Call<List<GalleriesResponse>> call, Response<List<GalleriesResponse>> response) {
                mGalleryView.setLoadingIndicator(false);
                if (response.code() == 200)
                    mGalleryView.showGalleries(response.body());
                else
                    mGalleryView.showMessage(response.message());
            }

            @Override
            public void onFailure(Call<List<GalleriesResponse>> call, Throwable t) {
                mGalleryView.setLoadingIndicator(false);
                mGalleryView.showConnectionError();
            }
        });
    }

    @Override
    public void getVideoGalleries() {
        Call<List<GalleriesResponse>> call = Injector.provideApiService().getVideoGalleries();

        mGalleryView.setLoadingIndicator(true);
        call.enqueue(new Callback<List<GalleriesResponse>>() {
            @Override
            public void onResponse(Call<List<GalleriesResponse>> call, Response<List<GalleriesResponse>> response) {
                mGalleryView.setLoadingIndicator(false);
                if (response.code() == 200)
                    mGalleryView.showGalleries(response.body());
                else
                    mGalleryView.showMessage(response.message());
            }

            @Override
            public void onFailure(Call<List<GalleriesResponse>> call, Throwable t) {
                mGalleryView.setLoadingIndicator(false);
                mGalleryView.showConnectionError();
            }
        });

    }

    GalleriesContract.View mGalleryView;
}
