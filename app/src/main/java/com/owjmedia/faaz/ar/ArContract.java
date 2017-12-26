package com.owjmedia.faaz.ar;

import com.owjmedia.faaz.ar.model.ArItem;
import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;

/**
 * Created by salman on 12/26/17.
 */

public interface ArContract {

    interface View extends BaseView<Presenter> {

        void setArItem(ArItem arItem);
    }

    interface Presenter extends BasePresenter {

        void getArItems();
    }
}
