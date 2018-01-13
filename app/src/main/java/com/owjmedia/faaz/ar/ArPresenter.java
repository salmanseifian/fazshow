package com.owjmedia.faaz.ar;

import com.owjmedia.faaz.ar.model.ArItem;
import com.owjmedia.faaz.general.networking.Injector;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by salman on 12/26/17.
 */

public class ArPresenter implements ArContract.Presenter {

    public ArPresenter(ArContract.View arView) {
        this.mArView = arView;
    }

    @Override
    public void getArItems() {
        Call<List<ArItem>> call = Injector.provideApiService().getArItems();

        mArView.setLoadingIndicator(true);
        call.enqueue(new Callback<List<ArItem>>() {
            @Override
            public void onResponse(Call<List<ArItem>> call, Response<List<ArItem>> response) {
                mArView.setLoadingIndicator(false);
                if (response.code() == 200)
                    mArView.setArItems(response.body());
                else
                    mArView.showMessage(response.message());
            }

            @Override
            public void onFailure(Call<List<ArItem>> call, Throwable t) {
                mArView.setLoadingIndicator(false);

            }
        });
    }

    private ArContract.View mArView;
}
