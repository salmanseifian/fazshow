package com.owjmedia.faaz.authenticate;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.AppManager;
import com.owjmedia.faaz.profile.ProfileFragment;

public class AuthenticateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication_act);
        if (!AppManager.isLogin(this)) {
            PhoneFragment phoneFragment = (PhoneFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
            if (phoneFragment == null) {
                phoneFragment = new PhoneFragment();
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), phoneFragment, R.id.contentFrame);
            }

            mAuthenticatePresenter = new AuthenticatePresenter(phoneFragment);
        } else {
            ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), new ProfileFragment(), R.id.contentFrame);
        }


    }

    private AuthenticatePresenter mAuthenticatePresenter;
}
