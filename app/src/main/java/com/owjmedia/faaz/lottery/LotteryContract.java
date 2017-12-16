package com.owjmedia.faaz.lottery;

import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;
import com.owjmedia.faaz.lottery.model.LotteryRequest;

/**
 * Created by salman on 12/13/17.
 */

public interface LotteryContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void consumeLotteryCode(String code);
    }
}
