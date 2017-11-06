package com.owjmedia.faaz.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by salman on 11/6/17.
 */

public class CustomTextView extends TextView {
    public CustomTextView(Context context) {
        super(context);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"IRANSans.ttf");
        this.setTypeface(typeface);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"IRANSans.ttf");
        this.setTypeface(typeface);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"IRANSans.ttf");
        this.setTypeface(typeface);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"IRANSans.ttf");
        this.setTypeface(typeface);
    }
}
