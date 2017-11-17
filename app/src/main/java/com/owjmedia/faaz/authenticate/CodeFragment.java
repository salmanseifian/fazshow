package com.owjmedia.faaz.authenticate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.AppManager;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefacedEditText;
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
    }

    @Override
    public void setPresenter(AuthenticateContract.Presenter presenter) {
        mPresenter = presenter;
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


    @OnClick(R.id.btnContinue)
    public void confirmPhoneNumber() {
        if (Validator.isStringValid(edtCode.getText().toString()))
            mPresenter.confirmPhoneNumber(getToken(), Integer.parseInt(edtCode.getText().toString()));
        else
            showMessage(getString(R.string.input_is_not_valid));
    }

    private String getToken() {
        return getArguments().getString(Constants.KEYS.TOKEN);
    }

    private String getExpiresIn() {
        return getArguments().getString(Constants.KEYS.EXPIRES_IN);
    }

    private AuthenticateContract.Presenter mPresenter;

    ProgressDialog mProgressDialog;

    @BindView(R.id.edtCode)
    TypefacedEditText edtCode;


}
