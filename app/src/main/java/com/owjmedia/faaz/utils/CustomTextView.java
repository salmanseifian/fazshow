package com.owjmedia.faaz.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;


public class CustomTextView extends TextView {
    public CustomTextView(Context context) {
        super(context);
        setTypeface(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setTypeface(context);
    }

    private void setTypeface(Context context) {
        Typeface regularTypeface = Typeface.createFromAsset(context.getAssets(), Constants.FONTS.FONT_PATH + Constants.FONTS.FONT_NAME_REGULAR);
        Typeface boldTypeface = Typeface.createFromAsset(context.getAssets(), Constants.FONTS.FONT_PATH + Constants.FONTS.FONT_NAME_BOLD);
        this.setTypeface(regularTypeface, Typeface.NORMAL);
        this.setTypeface(boldTypeface, Typeface.BOLD);
    }
}
