package com.owjmedia.faaz.general.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.authenticate.AuthenticateActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by salman on 11/11/17.
 */

public class AuthenticationDialog extends Dialog {

    public AuthenticationDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication_dlg);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.txtGoToAuthentication)
    public void goToAuthentication() {
        getContext().startActivity(new Intent(getContext(), AuthenticateActivity.class));
        cancel();
    }
}
