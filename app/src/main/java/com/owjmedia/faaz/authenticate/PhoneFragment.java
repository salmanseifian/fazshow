package com.owjmedia.faaz.authenticate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.owjmedia.faaz.R;

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
    }

    @OnClick(R.id.btnContinue)
    public void sendPhoneNumber() {
        mPresenter.sendPhoneNumber(edtPhone.getText().toString());
    }

    @Override
    public void setPresenter(AuthenticateContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showResponseCode(String code) {
        Toast.makeText(getActivity(), code, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLoadingIndicator() {

    }

    @Override
    public void showPhoneNumberSentSuccessfully(String token, int expire_in) {

    }


    @Override
    public void showAuthenticationCompleted() {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private AuthenticateContract.Presenter mPresenter;

    @BindView(R.id.edtPhone)
    TextInputEditText edtPhone;
}
