package com.owjmedia.faaz.gallerydetail;

import com.owjmedia.faaz.gallerydetail.model.GalleryDetailResponse;
import com.owjmedia.faaz.general.networking.Injector;
import com.owjmedia.faaz.general.networking.ApiService;

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
    public void getImageGalleryDetail(String galleryId) {
        Call<GalleryDetailResponse> call = Injector.provideApiService().getImageGalleryDetail(galleryId);

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
    public void getVideoGalleryDetail(String galleryId) {
        Call<GalleryDetailResponse> call = Injector.provideApiService().getVideoGalleryDetail(galleryId);

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
