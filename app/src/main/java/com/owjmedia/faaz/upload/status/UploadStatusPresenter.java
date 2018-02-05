package com.owjmedia.faaz.upload.status;

import android.support.annotation.NonNull;

import com.owjmedia.faaz.general.networking.Injector;
import com.owjmedia.faaz.upload.status.model.UploadStatus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UploadStatusPresenter implements UploadStatusContract.Presenter {

    public UploadStatusPresenter(UploadStatusContract.View view) {
        this.mView = view;
    }

    @Override
    public void getUploadStatus() {
        Call<UploadStatus> call = Injector.provideApiService().getUploadStatus();

        mView.setLoadingIndicator(true);
        call.enqueue(new Callback<UploadStatus>() {
            @Override
            public void onResponse(@NonNull Call<UploadStatus> call, @NonNull Response<UploadStatus> response) {
                mView.setLoadingIndicator(false);
                if (response.isSuccessful())
                    mView.showUploadStatus(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<UploadStatus> call, @NonNull Throwable t) {
                mView.setLoadingIndicator(false);
            }
        });
    }

    private UploadStatusContract.View mView;
}
