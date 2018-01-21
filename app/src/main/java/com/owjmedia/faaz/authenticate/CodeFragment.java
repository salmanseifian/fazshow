package com.owjmedia.faaz.authenticate;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.AppManager;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefaceEditText;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefaceTextView;
import com.owjmedia.faaz.general.utils.ProgressDialog;
import com.owjmedia.faaz.general.utils.Validator;
import com.owjmedia.faaz.profile.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by salman on 11/8/17.
 */

public class CodeFragment extends Fragment implements AuthenticateContract.View {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.code_frg, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mPresenter = new AuthenticatePresenter(this);

        mProgressDialog = new ProgressDialog(getContext());

        startTimer();
    }

    @Override
    public void onDestroy() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        super.onDestroy();
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long l) {
                txtTimer.setText(getString(R.string.remaining_time) + ": " + l / 1000 + " " + getString(R.string.seconds));
            }

            @Override
            public void onFinish() {
                txtTimer.setText(getString(R.string.resend_code));
            }
        }.start();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active)
            mProgressDialog.show();
        else
            mProgressDialog.cancel();

    }

    @Override
    public void showPhoneNumberSentSuccessfully(String token, int expire_in) {

    }

    @Override
    public void showAuthenticationCompleted(String accessToken) {
        AppManager.setString(getContext(), Constants.KEYS.TOKEN, "Bearer " + accessToken);
        ActivityUtils.replaceFragmentToActivity(getActivity().getSupportFragmentManager(), new ProfileFragment(), R.id.contentFrame);
    }

    @Override
    public void showMessage(String message) {
        ActivityUtils.showToast(getContext(), message, "emoji_shock.json");
    }

    @Override
    public void showConnectionError() {

    }


    @OnClick(R.id.btnContinue)
    public void confirmPhoneNumber() {
        if (Validator.isStringValid(edtCode.getText().toString()))
            mPresenter.confirmPhoneNumber(getToken(), Integer.parseInt(edtCode.getText().toString()));
        else
            showMessage(getString(R.string.input_is_not_valid));
    }

    @OnClick(R.id.txt_timer)
    public void resendCode() {
        mCountDownTimer.cancel();
        mPresenter.sendPhoneNumber(getPhone());
        startTimer();
    }

    private String getToken() {
        return getArguments().getString(Constants.KEYS.TOKEN);
    }

    private String getPhone() {
        return getArguments().getString(Constants.KEYS.PHONE);
    }

    private int getExpiresIn() {
        return getArguments().getInt(Constants.KEYS.EXPIRES_IN);
    }

    private AuthenticateContract.Presenter mPresenter;

    ProgressDialog mProgressDialog;

    CountDownTimer mCountDownTimer;

    @BindView(R.id.edtCode)
    TypefaceEditText edtCode;

    @BindView(R.id.txt_timer)
    TypefaceTextView txtTimer;


}
