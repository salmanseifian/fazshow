package com.owjmedia.faaz.ar;

import com.owjmedia.faaz.ar.model.ArItem;
import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;

import java.util.List;

/**
 * Created by salman on 12/26/17.
 */

public interface ArContract {

    interface View extends BaseView<Presenter> {

        void setArItems(List<ArItem> arItems);
    }

    interface Presenter extends BasePresenter {

        void getArItems();
    }
}
