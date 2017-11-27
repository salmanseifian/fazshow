package com.owjmedia.faaz.movie;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefacedTextView;
import com.owjmedia.faaz.general.utils.ImageHelper;

import agency.tango.materialintroscreen.SlideFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by seifian on 11/26/17.
 */

public class MovieFragment extends SlideFragment {

    public static MovieFragment createInstance(MovieSlideBuilder builder) {
        MovieFragment movieFragment = new MovieFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEYS.SLIDE_BACKGROUND_COLOR, builder.backgroundColor);
        bundle.putInt(Constants.KEYS.SLIDE_BUTTONS_COLOR, builder.buttonsColor);
        bundle.putString(Constants.KEYS.SLIDE_TITLE, builder.title);
        bundle.putString(Constants.KEYS.SLIDE_CONTENT, builder.content);
        bundle.putString(Constants.KEYS.SLIDE_IMAGE, builder.image);

        movieFragment.setArguments(bundle);
        return movieFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_frg, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initialize();
    }

    @Override
    public int backgroundColor() {
        return backgroundColor;
    }

    @Override
    public int buttonsColor() {
        return buttonsColor;
    }

    @Override
    public boolean canMoveFurther() {
        return true;
    }

    private void initialize() {
        Bundle bundle = getArguments();
        backgroundColor = bundle.getInt(Constants.KEYS.SLIDE_BACKGROUND_COLOR);
        buttonsColor = bundle.getInt(Constants.KEYS.SLIDE_BUTTONS_COLOR);
        title = bundle.getString(Constants.KEYS.SLIDE_TITLE);
        content = bundle.getString(Constants.KEYS.SLIDE_CONTENT);
        image = bundle.getString(Constants.KEYS.SLIDE_IMAGE);
        updateViewWithValues();
    }

    private void updateViewWithValues() {
        ImageHelper.getInstance(getContext()).imageLoader(image, imageSlide, ImageHelper.ImageType.GALLERY);
        txtTitle.setText(title);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtContent.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_COMPACT));
        } else {
            txtContent.setText(Html.fromHtml(content));
        }

    }

    private int backgroundColor;
    private int buttonsColor;
    private String title;
    private String content;
    private String image;

    @BindView(R.id.image_slide)
    ImageView imageSlide;

    @BindView(R.id.txt_title_slide)
    TypefacedTextView txtTitle;

    @BindView(R.id.txt_description_slide)
    TypefacedTextView txtContent;

}
