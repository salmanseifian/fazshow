package com.owjmedia.faaz.videodetail;

import com.google.android.exoplayer2.ExoPlayer;

/**
 * Created by salman on 1/19/18.
 */

public class ExoPlayerVideoHandler {

    private static ExoPlayerVideoHandler instance;

    private ExoPlayerVideoHandler() {
    }


    public static ExoPlayerVideoHandler getInstance() {
        if (instance == null)
            instance = new ExoPlayerVideoHandler();
        return instance;
    }
}
