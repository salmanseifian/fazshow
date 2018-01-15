package com.owjmedia.faaz.general;

import android.os.Build;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;

/**
 * Created by salman on 12/2/17.
 */

public class DetailsTransition extends TransitionSet {
    public DetailsTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setOrdering(ORDERING_TOGETHER);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                addTransition(new ChangeBounds()).
                        addTransition(new ChangeTransform()).
                        addTransition(new ChangeImageTransform());
            }
        }
    }
}
