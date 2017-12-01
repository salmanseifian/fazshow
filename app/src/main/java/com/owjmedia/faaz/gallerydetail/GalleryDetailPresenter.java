package com.owjmedia.faaz.gallerydetail;

import android.util.Log;

import com.owjmedia.faaz.galleries.model.GalleriesResponse;
import com.owjmedia.faaz.gallerydetail.model.GalleryDetailResponse;
import com.owjmedia.faaz.general.networking.ApiClient;
import com.owjmedia.faaz.general.networking.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by salman on 11/23/17.
 */

public class GalleryDetailPresenter implements GalleryDetailContract.Presenter {

    public GalleryDetailPresenter(GalleryDetailContract.View galleryDetailView) {
        mGalleryDetailView = galleryDetailView;
    }


    @Override
    public void getImageGalleryDetail(String token, String galleryId) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<GalleryDetailResponse> call = apiService.getImageGalleryDetail(token, galleryId);

        mGalleryDetailView.setLoadingIndicator(true);
        call.enqueue(new Callback<GalleryDetailResponse>() {
            @Override
            public void onResponse(Call<GalleryDetailResponse> call, Response<GalleryDetailResponse> response) {
                mGalleryDetailView.setLoadingIndicator(false);
                if (response.code() == 200)
                    mGalleryDetailView.showGalleryDetailResponse(response.body());
                else
                    mGalleryDetailView.showMessage(response.message());
            }

            @Override
            public void onFailure(Call<GalleryDetailResponse> call, Throwable t) {
                mGalleryDetailView.setLoadingIndicator(false);
            }
        });

    }

    @Override
    public void getVideoGalleryDetail(String token, String galleryId) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<GalleryDetailResponse> call = apiService.getVideoGalleryDetail(token, galleryId);

        mGalleryDetailView.setLoadingIndicator(true);
        call.enqueue(new Callback<GalleryDetailResponse>() {
            @Override
            public void onResponse(Call<GalleryDetailResponse> call, Response<GalleryDetailResponse> response) {
                mGalleryDetailView.setLoadingIndicator(false);
                if (response.code() == 200)
                    mGalleryDetailView.showGalleryDetailResponse(response.body());
                else
                    mGalleryDetailView.showMessage(response.message());
            }

            @Override
            public void onFailure(Call<GalleryDetailResponse> call, Throwable t) {
                mGalleryDetailView.setLoadingIndicator(false);
            }
        });
    }

    GalleryDetailContract.View mGalleryDetailView;
}
