package com.owjmedia.faaz.appinfo;

import com.owjmedia.faaz.appinfo.model.AppInfoResponse;
import com.owjmedia.faaz.gallerydetail.model.GalleryDetailResponse;
import com.owjmedia.faaz.general.networking.Injector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by salman on 12/10/17.
 */

public class AppInfoPresenter implements AppInfoContract.Presenter {

    public AppInfoPresenter(AppInfoContract.View AboutView) {
        mAboutView = AboutView;
    }

    @Override
    public void getAppInfo() {
        Call<AppInfoResponse> call = Injector.provideApiService().getAppInfo();

        mAboutView.setLoadingIndicator(true);
        call.enqueue(new Callback<AppInfoResponse>() {
            @Override
            public void onResponse(Call<AppInfoResponse> call, Response<AppInfoResponse> response) {
                mAboutView.setLoadingIndicator(false);
                if (response.code() == 200)
                    mAboutView.showAppInfo(response.body());
                else
                    mAboutView.showMessage(response.message());
            }

            @Override
            public void onFailure(Call<AppInfoResponse> call, Throwable t) {
                mAboutView.setLoadingIndicator(false);
                mAboutView.showConnectionError();
            }
        });
    }

    private AppInfoContract.View mAboutView;
}
