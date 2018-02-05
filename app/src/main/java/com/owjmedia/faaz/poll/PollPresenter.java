package com.owjmedia.faaz.poll;


import android.support.annotation.NonNull;

import com.owjmedia.faaz.general.networking.Injector;
import com.owjmedia.faaz.poll.model.Poll;
import com.owjmedia.faaz.poll.model.PollRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PollPresenter implements PollContract.Presenter {

    public PollPresenter(PollContract.View mPollView) {
        this.mPollView = mPollView;
    }

    @Override
    public void getPoll() {
        Call<Poll> call = Injector.provideApiService().getPoll();

        mPollView.setLoadingIndicator(true);
        call.enqueue(new Callback<Poll>() {
            @Override
            public void onResponse(@NonNull Call<Poll> call, @NonNull Response<Poll> response) {
                mPollView.setLoadingIndicator(false);
                if (response.isSuccessful())
                    mPollView.showPoll(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Poll> call, @NonNull Throwable t) {
                mPollView.setLoadingIndicator(false);
                mPollView.showConnectionError();
            }
        });
    }

    @Override
    public void participateInPoll(int pollId) {
        PollRequest pollRequest = new PollRequest(pollId);
        Call<ResponseBody> call = Injector.provideApiService().participateInPoll(pollRequest);

        mPollView.setLoadingIndicator(true);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                mPollView.setLoadingIndicator(false);
                if (response.isSuccessful()) {
                    mPollView.onPollVotedSuccessfully();
                } else
                    mPollView.showMessage(Injector.parseError(response).getDetail());
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                mPollView.setLoadingIndicator(false);
                mPollView.showConnectionError();
            }
        });
    }

    private PollContract.View mPollView;
}
