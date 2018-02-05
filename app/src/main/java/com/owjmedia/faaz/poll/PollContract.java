package com.owjmedia.faaz.poll;

import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;
import com.owjmedia.faaz.poll.model.Poll;
import com.owjmedia.faaz.poll.model.PollRequest;


public interface PollContract {

    interface View extends BaseView<Presenter> {

        void showPoll(Poll poll);

        void onPollVotedSuccessfully();

    }

    interface Presenter extends BasePresenter {

        void getPoll();

        void participateInPoll(int pollId);
    }
}
