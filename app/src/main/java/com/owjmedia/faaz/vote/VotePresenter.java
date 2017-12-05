package com.owjmedia.faaz.vote;

import com.owjmedia.faaz.vote.model.VotingResponse;
import com.owjmedia.faaz.general.networking.Injector;
import com.owjmedia.faaz.general.networking.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by salman on 11/10/17.
 */

public class VotePresenter implements VoteContract.Presenter {

    public VotePresenter(VoteContract.View mVotingContract) {
        this.mVotingView = mVotingContract;
    }


    @Override
    public void getVotings(String pollType) {
        Call<List<VotingResponse>> call = Injector.provideApiService().getVotings(pollType);

        mVotingView.setLoadingIndicator(true);
        call.enqueue(new Callback<List<VotingResponse>>() {
            @Override
            public void onResponse(Call<List<VotingResponse>> call, Response<List<VotingResponse>> response) {
                mVotingView.setLoadingIndicator(false);
                mVotingView.showVotings(response.body());
            }

            @Override
            public void onFailure(Call<List<VotingResponse>> call, Throwable t) {
                mVotingView.setLoadingIndicator(false);
                mVotingView.showMessage(t.getMessage());

            }
        });

    }

    private final VoteContract.View mVotingView;

}
