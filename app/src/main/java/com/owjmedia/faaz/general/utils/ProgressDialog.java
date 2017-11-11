package com.owjmedia.faaz.general.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;

import com.owjmedia.faaz.R;

/**
 * Created by salman on 11/11/17.
 */

public class ProgressDialog extends Dialog {

    public ProgressDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.progress_dlg);
    }

    public static ProgressDialog getInstance(Context context) {
        if (instance == null)
            instance = new ProgressDialog(context);
        return instance;
    }



    private static ProgressDialog instance;
}
