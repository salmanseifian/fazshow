package com.owjmedia.faaz.movie;

import android.support.annotation.DrawableRes;

import agency.tango.materialintroscreen.SlideFragment;
import agency.tango.materialintroscreen.SlideFragmentBuilder;

/**
 * Created by seifian on 11/26/17.
 */

public class MovieSlideBuilder {

    int backgroundColor;
    int buttonsColor;
    String title;
    String content;
    String image;

    public MovieSlideBuilder backgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public MovieSlideBuilder buttonsColor(int buttonsColor) {
        this.buttonsColor = buttonsColor;
        return this;
    }

    public MovieSlideBuilder title(String title) {
        this.title = title;
        return this;
    }

    public MovieSlideBuilder content(String content) {
        this.content = content;
        return this;
    }

    public MovieSlideBuilder image(String image) {
        this.image = image;
        return this;
    }

    public SlideFragment build() {
        return MovieFragment.createInstance(this);
    }
}
