package com.owjmedia.faaz.votedetail;

import com.owjmedia.faaz.votedetail.model.VoteDetailResponse;
import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;


interface VoteDetailContract {

    interface View extends BaseView<Presenter> {

        void showCandidates(VoteDetailResponse voteDetailResponse);

        void votedSuccessfully();
    }

    interface Presenter extends BasePresenter {

        void getCandidates(String pollId);

        void vote(String pollId, int itemId);

    }
}
