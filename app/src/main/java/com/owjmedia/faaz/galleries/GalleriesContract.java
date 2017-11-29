package com.owjmedia.faaz.galleries;

import com.owjmedia.faaz.galleries.model.GalleriesResponse;
import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;

import java.util.List;

/**
 * Created by salman on 11/23/17.
 */

public interface GalleriesContract {

    interface View extends BaseView<Presenter> {

        void showGalleries(List<GalleriesResponse> galleriesResponses);

    }

    interface Presenter extends BasePresenter {

        void getImageGalleries(String token);

        void getVideoGalleries(String token);

    }
}
