package com.owjmedia.faaz.general.utils;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.owjmedia.faaz.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


/**
 * Created by salman on 11/11/17.
 */

public class ImageHelper {

    public ImageHelper(Context context) {
        this.context = context;
    }

    public static ImageHelper getInstance(Context context) {
        if (instance == null)
            instance = new ImageHelper(context);
        return instance;
    }

    public void imageLoader(String imageUrl, ImageView imageView, ImageType imageType) {
        switch (imageType) {
            case AVATAR:
                Picasso.with(context)
                        .load(imageUrl)
                        .transform(getTransformation())
                        .placeholder(R.drawable.avatar)
                        .into(imageView);
                break;
            case NEWS:
                Picasso.with(context)
                        .load(imageUrl)
                        .fit()
                        .transform(getTransformation())
                        .placeholder(R.drawable.placeholder_news)
                        .into(imageView);
                break;

            case GALLERY:
                Picasso.with(context)
                        .load(imageUrl)
                        .placeholder(R.drawable.placeholder_news)
                        .into(imageView);
                break;
            default:
                break;
        }

    }

    private Transformation getTransformation() {

        return new RoundedTransformationBuilder()
                .cornerRadiusDp(16)
                .oval(false)
                .build();

    }

    public enum ImageType {
        NEWS, AVATAR, GALLERY
    }

    private static ImageHelper instance;
    private Context context;
}
