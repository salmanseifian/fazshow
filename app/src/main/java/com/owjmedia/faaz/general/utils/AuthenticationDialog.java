package com.owjmedia.faaz.general.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.authenticate.AuthenticateActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by salman on 11/11/17.
 */

public class AuthenticationDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.authentication_dlg, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.txtGoToAuthentication)
    public void goToAuthentication() {
        getContext().startActivity(new Intent(getContext(), AuthenticateActivity.class));
        dismiss();
    }
}
