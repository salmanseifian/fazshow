package com.owjmedia.faaz.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;

import java.util.List;

import agency.tango.materialintroscreen.MaterialIntroActivity;

/**
 * Created by seifian on 11/26/17.
 */

public class IntroActivity extends MaterialIntroActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> titles = getIntent().getStringArrayListExtra(Constants.KEYS.SLIDE_TITLE);
        List<String> contents = getIntent().getStringArrayListExtra(Constants.KEYS.SLIDE_CONTENT);
        List<String> images = getIntent().getStringArrayListExtra(Constants.KEYS.SLIDE_IMAGE);
        int[] backgroundColors = {R.color.first_slide_background, R.color.second_slide_background,
                R.color.third_slide_background, R.color.fourth_slide_background};
        int[] buttonColors = {R.color.first_slide_buttons, R.color.second_slide_buttons,
                R.color.third_slide_buttons, R.color.fourth_slide_buttons};

        for (int i = 0; i < titles.size(); i++) {
            addSlide(new MovieSlideBuilder()
                    .backgroundColor(backgroundColors[i])
                    .buttonsColor(buttonColors[i])
                    .title(titles.get(i))
                    .content(contents.get(i))
                    .image(images.get(i))
                    .build());
        }
    }
}
