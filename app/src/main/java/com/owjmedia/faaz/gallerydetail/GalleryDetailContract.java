package com.owjmedia.faaz.gallerydetail;

import com.owjmedia.faaz.galleries.model.GalleriesResponse;
import com.owjmedia.faaz.gallerydetail.model.GalleryDetailResponse;
import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;

import java.util.List;

/**
 * Created by salman on 11/23/17.
 */

public interface GalleryDetailContract {

    interface View extends BaseView<Presenter> {

        void showGalleryDetailResponse(GalleryDetailResponse galleryDetailResponse);

    }

    interface Presenter extends BasePresenter {

        void getImageGalleryDetail(String galleryId);

        void getVideoGalleryDetail(String galleryId);

    }
}
