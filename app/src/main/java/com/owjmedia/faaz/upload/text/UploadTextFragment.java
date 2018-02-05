package com.owjmedia.faaz.upload.text;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.ConnectionErrorDialog;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefaceEditText;
import com.owjmedia.faaz.general.utils.ProgressDialog;
import com.owjmedia.faaz.general.utils.Validator;
import com.owjmedia.faaz.upload.status.UploadActivity;


public class UploadTextFragment extends Fragment implements UploadTextContract.View {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.upload_text_frg, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressDialog = new ProgressDialog(getActivity());
        edtTitle = view.findViewById(R.id.edt_title);
        edtDescription = view.findViewById(R.id.edt_description);
        view.findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validator.isStringValid(edtTitle.getText().toString()) &&
                        Validator.isStringValid(edtDescription.getText().toString())) {
                    new UploadTextPresenter(UploadTextFragment.this).uploadText(
                            edtTitle.getText().toString(),
                            edtDescription.getText().toString()
                    );
                } else {
                    ActivityUtils.showToast(getContext(), getString(R.string.input_is_not_valid), "emoji_shock.json");

                }
            }
        });
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
        ActivityUtils.showToast(getActivity(), message);
    }

    @Override
    public void showConnectionError() {
        new ConnectionErrorDialog().show(getFragmentManager(), null);
    }

    @Override
    public void onSuccessfullyUploaded() {
        getActivity().finish();
        Intent intent = new Intent(getContext(), UploadActivity.class);
        startActivity(intent);
    }

    ProgressDialog mProgressDialog;
    private TypefaceEditText edtTitle;
    private TypefaceEditText edtDescription;
}
