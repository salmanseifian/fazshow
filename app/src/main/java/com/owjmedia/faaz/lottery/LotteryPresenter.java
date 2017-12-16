package com.owjmedia.faaz.lottery;

import com.owjmedia.faaz.appinfo.model.AppInfoResponse;
import com.owjmedia.faaz.general.networking.Injector;
import com.owjmedia.faaz.lottery.model.LotteryRequest;
import com.owjmedia.faaz.lottery.model.LotteryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by salman on 12/13/17.
 */

public class LotteryPresenter implements LotteryContract.Presenter {


    public LotteryPresenter(LotteryContract.View lotteryContractView) {
        mLotteryContractView = lotteryContractView;
    }

    @Override
    public void consumeLotteryCode(String code) {
        Call<LotteryResponse> call = Injector.provideApiService().consumeLotteryCode(new LotteryRequest(code));

        mLotteryContractView.setLoadingIndicator(true);
        call.enqueue(new Callback<LotteryResponse>() {
            @Override
            public void onResponse(Call<LotteryResponse> call, Response<LotteryResponse> response) {
                mLotteryContractView.setLoadingIndicator(false);

                if (response.code() == 200)
                    mLotteryContractView.showMessage(response.body().getStatus());
                else
                    mLotteryContractView.showMessage(response.message());

            }

            @Override
            public void onFailure(Call<LotteryResponse> call, Throwable t) {
                mLotteryContractView.setLoadingIndicator(false);
                mLotteryContractView.showConnectionError();

            }
        });
    }

    private LotteryContract.View mLotteryContractView;
}
