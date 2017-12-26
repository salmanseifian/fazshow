package com.owjmedia.faaz.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.AppManager;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefacedEditText;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefacedTextView;
import com.owjmedia.faaz.general.utils.ProgressDialog;
import com.owjmedia.faaz.general.utils.Validator;
import com.owjmedia.faaz.profile.model.ProfileResponse;

import java.util.ArrayList;
import java.util.List;

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

        mProfilePresenter.getProfile();

        // Set up spinner
        setUpSpinner();
    }

    private void setUpSpinner() {
        List<String> list = new ArrayList<>();
        list.add("جنسیت");
        list.add(getString(R.string.male_persian));
        list.add(getString(R.string.female_persian));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(),
                R.layout.item_spinner, list);
        dataAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        spinnerGender.setAdapter(dataAdapter);
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
    public void showConnectionError() {

    }

    @Override
    public void profileUpdatedSuccessfully() {
        getActivity().finish();
    }

    @Override
    public void showProfile(ProfileResponse profileResponse) {
        txtScore.setText(String.valueOf(profileResponse.getScore()));
        spinnerGender.setSelection(profileResponse.getGender(), true);
        edtCity.setText(profileResponse.getCity());
        if (profileResponse.getYearOfBirth() != 0)
            edtBirthYear.setText(String.valueOf(profileResponse.getYearOfBirth()));
    }

    @OnClick(R.id.btnContinue)
    public void saveProfile() {
        if (Validator.isStringValid(spinnerGender.getSelectedItem().toString()) && !spinnerGender.getSelectedItem().toString().equals(getString(R.string.gender)) && Validator.isStringValid(edtCity.getText().toString()) && Validator.isStringValid(edtBirthYear.getText().toString()))
            mProfilePresenter.updateProfile(AppManager.getGenderNumber(getContext(), spinnerGender.getSelectedItem().toString()), edtCity.getText().toString(),
                    Integer.parseInt(edtBirthYear.getText().toString()));
        else
            showMessage(getString(R.string.inputs_is_not_valid));
    }

    @OnClick(R.id.txtSkip)
    public void skip() {
        getActivity().finish();
    }


    private ProfileContract.Presenter mProfilePresenter;

    ProgressDialog mProgressDialog;

    @BindView(R.id.txt_score)
    TypefacedTextView txtScore;

    @BindView(R.id.edtGender)
    TypefacedEditText edtGender;

    @BindView(R.id.spinnerGender)
    Spinner spinnerGender;

    @BindView(R.id.edtCity)
    TypefacedEditText edtCity;

    @BindView(R.id.edtBirthYear)
    TypefacedEditText edtBirthYear;


}
