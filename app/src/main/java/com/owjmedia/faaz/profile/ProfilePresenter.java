package com.owjmedia.faaz.profile;

import com.owjmedia.faaz.data.AuthenticationRequest;
import com.owjmedia.faaz.data.AuthenticationResponse;
import com.owjmedia.faaz.data.UpdateProfileRequest;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.networking.ApiClient;
import com.owjmedia.faaz.general.networking.ApiInterface;
import com.owjmedia.faaz.general.utils.AppSettings;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by salman on 11/9/17.
 */

public class ProfilePresenter implements ProfileContract.Presenter {

    private final ProfileContract.View mProfileView;

    public ProfilePresenter(ProfileContract.View profileView) {
        mProfileView = profileView;

        mProfileView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void updateProfile(String token, String gender, String city, int year_of_birth) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest(gender, city, year_of_birth);
        Call<ResponseBody> call = apiService.updateProfile(token, updateProfileRequest);
        mProfileView.setLoadingIndicator(true);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mProfileView.setLoadingIndicator(false);
                mProfileView.profileUpdatedSuccessfully();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mProfileView.setLoadingIndicator(false);
                mProfileView.showMessage(t.getMessage());
            }
        });
    }
}
