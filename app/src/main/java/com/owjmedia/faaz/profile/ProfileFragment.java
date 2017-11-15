package com.owjmedia.faaz.profile;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by salman on 11/9/17.
 */

public class ProfileFragment extends Fragment implements ProfileContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_frg, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mProfilePresenter = new ProfilePresenter(this);

        mProgressDialog = new ProgressDialog(getContext());
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mProfilePresenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active)
            mProgressDialog.show();
        else
            mProgressDialog.cancel();
    }

    @Override
    public void showMessage(String message) {
        ActivityUtils.showToast(getContext(), message, "emoji_shock.json");
    }

    @Override
    public void profileUpdatedSuccessfully() {
        getActivity().finish();
    }

    @OnClick(R.id.btnContinue)
    public void saveProfile() {
        if (Validator.isStringValid(edtGender.getText().toString()) && Validator.isStringValid(edtCity.getText().toString()) && Validator.isStringValid(edtBirthYear.getText().toString()))
            mProfilePresenter.updateProfile(AppManager.getString(getContext(), Constants.KEYS.TOKEN), edtGender.getText().toString(), edtCity.getText().toString(),
                    Integer.parseInt(edtBirthYear.getText().toString()));
        else
            showMessage(getString(R.string.inputs_is_not_valid));
    }


    private ProfileContract.Presenter mProfilePresenter;

    ProgressDialog mProgressDialog;

    @BindView(R.id.edtGender)
    TypefacedEditText edtGender;

    @BindView(R.id.edtCity)
    TypefacedEditText edtCity;

    @BindView(R.id.edtBirthYear)
    TypefacedEditText edtBirthYear;


}
