package com.owjmedia.faaz.upload.text;

import android.support.annotation.NonNull;

import com.owjmedia.faaz.general.networking.Injector;
import com.owjmedia.faaz.upload.text.model.UploadTextRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by salman on 1/21/18.
 */

public class UploadTextPresenter implements UploadTextContract.Presenter {

    public UploadTextPresenter(UploadTextContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void uploadText(String title, String description) {
        UploadTextRequest uploadTextRequest = new UploadTextRequest("default",
                description,
                title
        );
        Call<ResponseBody> call = Injector.provideApiService().uploadText(uploadTextRequest);

        mView.setLoadingIndicator(true);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                mView.setLoadingIndicator(false);
                if (response.isSuccessful())
                    mView.onSuccessfullyUploaded();
                else
                    mView.showMessage(Injector.parseError(response).getDetail());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mView.setLoadingIndicator(false);
                mView.showConnectionError();
            }
        });
    }

    private UploadTextContract.View mView;
}
