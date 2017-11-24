package com.owjmedia.faaz.general.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by salman on 11/24/17.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    public SpaceItemDecoration(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;

        if (parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1)
            outRect.top = mSpace;
        else
            outRect.top = 0;

    }

    private int mSpace;
}
