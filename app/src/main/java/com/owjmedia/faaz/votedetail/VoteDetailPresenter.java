package com.owjmedia.faaz.votedetail;

import com.owjmedia.faaz.votedetail.model.VoteDetailRequest;
import com.owjmedia.faaz.votedetail.model.VoteDetailResponse;
import com.owjmedia.faaz.general.networking.ApiClient;
import com.owjmedia.faaz.general.networking.ApiInterface;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by salman on 11/11/17.
 */

public class VoteDetailPresenter implements VoteDetailContract.Presenter {

    public VoteDetailPresenter(VoteDetailContract.View voteDetailView) {
        this.mVoteDetailView = voteDetailView;

        mVoteDetailView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getCandidates(String accessToken, String pollId) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<VoteDetailResponse> call = apiService.getCandidates(accessToken, pollId);

        mVoteDetailView.setLoadingIndicator(true);
        call.enqueue(new Callback<VoteDetailResponse>() {
            @Override
            public void onResponse(Call<VoteDetailResponse> call, Response<VoteDetailResponse> response) {
                mVoteDetailView.setLoadingIndicator(false);
                if (response.code() == 200)
                    mVoteDetailView.showCandidates(response.body());
                else
                    mVoteDetailView.showMessage(response.message());

            }

            @Override
            public void onFailure(Call<VoteDetailResponse> call, Throwable t) {
                mVoteDetailView.setLoadingIndicator(false);
            }
        });
    }

    @Override
    public void vote(String accessToken, String pollId, int itemId) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        VoteDetailRequest voteDetailRequest = new VoteDetailRequest(itemId);
        Call<ResponseBody> call = apiService.vote(accessToken, pollId, voteDetailRequest);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mVoteDetailView.votedSuccessfully();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    VoteDetailContract.View mVoteDetailView;
}
