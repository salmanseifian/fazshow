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
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefacedTextView;
import com.wang.avi.AVLoadingIndicatorView;

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
    public void setLoadingIndicator(boolean active) {
        if (active) {
            btnContinue.setVisibility(View.INVISIBLE);
            prg.show();
        } else {
            btnContinue.setVisibility(View.INVISIBLE);
            prg.setVisibility(View.INVISIBLE);
            prg.hide();
        }
    }

    @Override
    public void showPhoneNumberSentSuccessfully(String token, int expire_in) {
        CodeFragment codeFragment = new CodeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEYS.TOKEN, token);
        bundle.putInt(Constants.KEYS.EXPIRES_IN, expire_in);
        codeFragment.setArguments(bundle);
        ActivityUtils.addFragmentToActivity(getActivity().getSupportFragmentManager(), codeFragment, R.id.contentFrame);
    }

    @Override
    public void showAuthenticationCompleted(String accessToken) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private AuthenticateContract.Presenter mPresenter;

    @BindView(R.id.edtPhone)
    TextInputEditText edtPhone;

    @BindView(R.id.btnContinue)
    TypefacedTextView btnContinue;

    @BindView(R.id.prg)
    AVLoadingIndicatorView prg;

}
