package com.owjmedia.faaz.upload.text;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefaceEditText;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefaceTextView;
import com.owjmedia.faaz.general.utils.Validator;
import com.owjmedia.faaz.upload.image.UploadImageFragment;
import com.owjmedia.faaz.upload.text.model.UploadTextRequest;

/**
 * Created by salman on 1/21/18.
 */

public class UploadTextFragment extends Fragment implements UploadTextContract.View {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.upload_text_frg, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showConnectionError() {

    }

    @Override
    public void onSuccessfullyUploaded() {
        ActivityUtils.replaceFragmentToActivitySlidly(getFragmentManager(), new UploadImageFragment(), R.id.contentFrame);
    }

    private TypefaceEditText edtTitle;
    private TypefaceEditText edtDescription;
}
