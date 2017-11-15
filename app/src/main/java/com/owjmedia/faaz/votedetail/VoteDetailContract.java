package com.owjmedia.faaz.votedetail;

import com.owjmedia.faaz.data.Item;
import com.owjmedia.faaz.data.VoteDetailResponse;
import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;

import java.util.List;


interface VoteDetailContract {

    interface View extends BaseView<Presenter> {

        void showCandidates(VoteDetailResponse voteDetailResponse);

        void votedSuccessfully();
    }

    interface Presenter extends BasePresenter {

        void getCandidates(String pollId);

        void vote(String accessToken, String pollId, int itemId);

    }
}
