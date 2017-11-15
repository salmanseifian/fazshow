package com.owjmedia.faaz.vote;

import com.owjmedia.faaz.data.VotingResponse;
import com.owjmedia.faaz.general.networking.ApiClient;
import com.owjmedia.faaz.general.networking.ApiInterface;

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

        mVotingView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void getVotings(String pollType) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<VotingResponse>> call = apiService.getVotings(pollType);

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
