package com.owjmedia.faaz.authenticate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.AppSettings;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefacedTextView;
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
    }

    @OnClick(R.id.btnContinue)
    public void confirmPhoneNumber() {
        mPresenter.confirmPhoneNumber(getToken(), Integer.parseInt(edtCode.getText().toString()));
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
            prg.setVisibility(View.VISIBLE);
        } else {
            btnContinue.setVisibility(View.INVISIBLE);
            prg.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showPhoneNumberSentSuccessfully(String token, int expire_in) {

    }

    @Override
    public void showAuthenticationCompleted(String accessToken) {
        AppSettings.setString(getContext(), Constants.KEYS.TOKEN, accessToken);
        ActivityUtils.replaceFragmentToActivity(getActivity().getSupportFragmentManager(), new ProfileFragment(), R.id.contentFrame);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private String getToken() {
        return getArguments().getString(Constants.KEYS.TOKEN);
    }

    private String getExpiresIn() {
        return getArguments().getString(Constants.KEYS.EXPIRES_IN);
    }

    private AuthenticateContract.Presenter mPresenter;


    @BindView(R.id.edtCode)
    TextInputEditText edtCode;

    @BindView(R.id.btnContinue)
    TypefacedTextView btnContinue;

    @BindView(R.id.prg)
    ProgressBar prg;
}
