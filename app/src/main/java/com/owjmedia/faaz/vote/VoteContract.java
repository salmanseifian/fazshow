package com.owjmedia.faaz.vote;

import com.owjmedia.faaz.vote.model.VotingResponse;
import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;

import java.util.List;

/**
 * Created by salman on 11/10/17.
 */

public interface VoteContract {

    interface View extends BaseView<Presenter> {

        void showVotings(List<VotingResponse> votingResponses);
    }

    interface Presenter extends BasePresenter {

        void getVotings(String pollType);

    }
}
