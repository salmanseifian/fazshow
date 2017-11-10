package com.owjmedia.faaz.general.utils.CustomWidgets;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.owjmedia.faaz.general.Constants;

/**
 * Created by salman on 11/10/17.
 */

public class TypefacedTabLayout extends TabLayout {

    private Typeface mTypeFace;

    public TypefacedTabLayout(Context context) {
        super(context);
        init(context);
    }

    public TypefacedTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TypefacedTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mTypeFace = Typeface.createFromAsset(context.getAssets(), Constants.FONTS.FONT_PATH + Constants.FONTS.FONT_NAME_REGULAR);
    }

    @Override
    public void addTab(@NonNull Tab tab) {

        super.addTab(tab);

//        ViewGroup tabView = (ViewGroup) ((ViewGroup) getChildAt(0)).getChildAt(tab.getPosition());
//        int tabChildCount = tabView.getChildCount();
//        for (int i = 0; i < tabChildCount; i++) {
//            View tabChild = tabView.getChildAt(i);
//            if (tabChild instanceof TextView) {
//                ((TextView) tabChild).setTypeface(mTypeFace);
//            }
//        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        final ViewGroup tabStrip = (ViewGroup) getChildAt(0);
        final int tabCount = tabStrip.getChildCount();
        ViewGroup tabView;
        int tabChildCount;
        View tabViewChild;

        for (int i = 0; i < tabCount; i++) {
            tabView = (ViewGroup) tabStrip.getChildAt(i);
            tabChildCount = tabView.getChildCount();
            for (int j = 0; j < tabChildCount; j++) {
                tabViewChild = tabView.getChildAt(j);
                if (tabViewChild instanceof TextView) {
                    if (mTypeFace == null) {
                        mTypeFace = Typeface.createFromAsset(getContext().getAssets(), Constants.FONTS.FONT_PATH + Constants.FONTS.FONT_NAME_REGULAR);
                    }
                    ((TextView) tabViewChild).setTypeface(mTypeFace, Typeface.NORMAL);
                }
            }
        }
    }
}
