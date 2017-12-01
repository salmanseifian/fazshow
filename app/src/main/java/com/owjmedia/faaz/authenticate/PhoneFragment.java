package com.owjmedia.faaz.authenticate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefacedEditText;
import com.owjmedia.faaz.general.utils.ProgressDialog;
import com.owjmedia.faaz.general.utils.Validator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by salman on 11/8/17.
 */

public class PhoneFragment extends Fragment implements AuthenticateContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.phone_frg, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mPresenter = new AuthenticatePresenter(this);

        mProgressDialog = new ProgressDialog(getContext());
    }

    @OnClick(R.id.btnContinue)
    public void sendPhoneNumber() {
        if (Validator.isPhoneNumberValid(edtPhone.getText().toString()))
            mPresenter.sendPhoneNumber(edtPhone.getText().toString());
        else
            ActivityUtils.showToast(getContext(), getString(R.string.input_is_not_valid), "emoji_shock.json");
    }


    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {
            mProgressDialog.show();
        } else {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void showPhoneNumberSentSuccessfully(String token, int expire_in) {
        CodeFragment codeFragment = new CodeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEYS.TOKEN, token);
        bundle.putString(Constants.KEYS.PHONE, edtPhone.getText().toString());
        bundle.putInt(Constants.KEYS.EXPIRES_IN, expire_in);
        codeFragment.setArguments(bundle);
        ActivityUtils.addFragmentToActivity(getActivity().getSupportFragmentManager(), codeFragment, R.id.contentFrame);
    }

    @Override
    public void showAuthenticationCompleted(String accessToken) {

    }

    @Override
    public void showMessage(String message) {
        ActivityUtils.showToast(getContext(), message, "emoji_shock.json");
    }

    @Override
    public void showConnectionError() {

    }

    private AuthenticateContract.Presenter mPresenter;

    ProgressDialog mProgressDialog;

    @BindView(R.id.edtPhone)
    TypefacedEditText edtPhone;

}
