package com.owjmedia.faaz.movie;

import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;
import com.owjmedia.faaz.movie.model.MovieResponse;

import java.util.List;

/**
 * Created by seifian on 11/26/17.
 */

public interface MovieContract {

    interface View extends BaseView<Presenter> {
        void showMovie(List<MovieResponse> movieResponses);
    }

    interface Presenter extends BasePresenter {

        void getMovie();
    }
}
