package com.owjmedia.faaz.votedetail;

import com.owjmedia.faaz.general.model.DetailResponse;
import com.owjmedia.faaz.votedetail.model.VoteDetailRequest;
import com.owjmedia.faaz.votedetail.model.VoteDetailResponse;
import com.owjmedia.faaz.general.networking.Injector;
import com.owjmedia.faaz.general.networking.ApiService;

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
    }

    @Override
    public void getCandidates(String pollId) {
        Call<VoteDetailResponse> call = Injector.provideApiService().getCandidates(pollId);

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
    public void vote(String pollId, int itemId) {
        VoteDetailRequest voteDetailRequest = new VoteDetailRequest(itemId);
        Call<ResponseBody> call = Injector.provideApiService().vote(pollId, voteDetailRequest);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200 || response.code() == 201)
                    mVoteDetailView.votedSuccessfully();
                else if (response.code() == 400)
                    mVoteDetailView.showMessage("رای خود را ثبت نموده اید");
                else
                    mVoteDetailView.showMessage(Injector.parseError(response).getDetail());

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    VoteDetailContract.View mVoteDetailView;
}
