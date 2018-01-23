package com.owjmedia.faaz.upload.image;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.networking.ProgressRequestBody;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefaceTextView;

import java.io.File;

import okhttp3.MultipartBody;

/**
 * Created by salman on 1/19/18.
 */

public class UploadImageFragment extends DialogFragment implements UploadImageContract.View, ProgressRequestBody.UploadCallbacks {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.upload_image_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActivityUtils.verifyStoragePermissions(getActivity());
        btnChooseImageFile = view.findViewById(R.id.btn_choose_image_file);
        btnUploadImage = view.findViewById(R.id.btn_upload_image);
        imgChosen = view.findViewById(R.id.img_chosen);
        prgUpload = view.findViewById(R.id.prg_upload);
        btnChooseImageFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGallery();
            }
        });
        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imagePath != null)
                    upload(imagePath);

            }
        });
    }

    public void showGallery() {
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/jpg");
        galleryIntent.setAction(Intent.ACTION_PICK);
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Choose image");
        startActivityForResult(chooserIntent, 100);
    }


    private void upload(String imagePath) {
        File file = new File(imagePath);
        ProgressRequestBody fileBody = new ProgressRequestBody(file, this);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("image", file.getName(), fileBody);
        new UploadImagePresenter(this).uploadImage(filePart);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            if (data == null) {
                ActivityUtils.showToast(getActivity(), "Unable to pick image");
                return;
            }
            Uri imageUri = data.getData();
            imgChosen.setImageURI(imageUri);
            imagePath = ActivityUtils.getRealPathFromURI(imageUri, getActivity().getApplicationContext());
            btnChooseImageFile.setText(getString(R.string.change_image_file));
            btnUploadImage.setVisibility(View.VISIBLE);
            prgUpload.setVisibility(View.VISIBLE);

        }
    }


    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showMessage(String message) {
        ActivityUtils.showToast(getActivity(), message);
        dismiss();
    }

    @Override
    public void showConnectionError() {

    }

    @Override
    public void onImageUploadedSuccessfully() {
        dismiss();
    }

    @Override
    public void onProgressUpdate(int percentage) {
        prgUpload.setProgress(percentage);
    }

    @Override
    public void onError() {

    }

    @Override
    public void finish() {
        prgUpload.setProgress(100);
    }

    private String imagePath;
    ImageView imgChosen;
    TypefaceTextView btnChooseImageFile;
    TypefaceTextView btnUploadImage;
    ProgressBar prgUpload;
}
