package com.owjmedia.faaz.movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.AppManager;
import com.owjmedia.faaz.general.utils.ProgressDialog;
import com.owjmedia.faaz.movie.model.MovieResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seifian on 11/26/17.
 */

public class MovieActivity extends AppCompatActivity implements MovieContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_act);

        mMoviePresenter = new MoviePresenter(this);
        mProgressDialog = new ProgressDialog(this);
        mMoviePresenter.getMovie(AppManager.getToken(this));

    }

    @Override
    public void setPresenter(MovieContract.Presenter presenter) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active)
            mProgressDialog.show();
        else
            mProgressDialog.cancel();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMovie(List<MovieResponse> movieResponses) {
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> contents = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();
        for (MovieResponse movieResponse : movieResponses) {
            titles.add(movieResponse.getTitle());
            contents.add(movieResponse.getContent());
            images.add(movieResponse.getImage());
        }
        Intent intro = new Intent(MovieActivity.this, IntroActivity.class);
        intro.putStringArrayListExtra(Constants.KEYS.SLIDE_TITLE, titles);
        intro.putStringArrayListExtra(Constants.KEYS.SLIDE_CONTENT, contents);
        intro.putStringArrayListExtra(Constants.KEYS.SLIDE_IMAGE, images);
        startActivity(intro);
        finish();
    }


    private MovieContract.Presenter mMoviePresenter;
    private ProgressDialog mProgressDialog;
}
