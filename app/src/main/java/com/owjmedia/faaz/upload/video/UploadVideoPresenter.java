package com.owjmedia.faaz.upload.video;

import com.owjmedia.faaz.general.networking.Injector;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by salman on 1/22/18.
 */

public class UploadVideoPresenter implements UploadVideoContract.Presenter {

    public UploadVideoPresenter(UploadVideoContract.View view) {
        this.view = view;
    }

    @Override
    public void uploadVideo(MultipartBody.Part part) {
        Call<ResponseBody> call = Injector.provideApiService().uploadVideo(part);

        view.setLoadingIndicator(true);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.setLoadingIndicator(false);
                if (response.isSuccessful())
                    view.onVideoUploaded();
                else
                    view.showMessage(Injector.parseError(response).getDetail());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.setLoadingIndicator(false);
                view.showConnectionError();
            }
        });
    }

    private UploadVideoContract.View view;


}
