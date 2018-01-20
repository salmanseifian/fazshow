package com.owjmedia.faaz.upload;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefaceTextView;

/**
 * Created by salman on 1/19/18.
 */

public class UploadImageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.upload_image_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActivityUtils.verifyStoragePermissions(getActivity());
        btnChooseImageFile = view.findViewById(R.id.btn_choose_image_file);
        btnUploadImage = view.findViewById(R.id.btn_upload_image);
        imgChosen = view.findViewById(R.id.img_chosen);
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
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_PICK);

        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Choose image");
        startActivityForResult(chooserIntent, 100);
    }


    private void upload(String imagePath) {
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            if (data == null) {
                ActivityUtils.showToast(getContext(), "Unable to pick image");
                return;
            }
            Uri imageUri = data.getData();
            imgChosen.setImageURI(imageUri);

            imagePath = ActivityUtils.getRealPathFromURI(imageUri, getContext().getApplicationContext());

        }
    }

    private String imagePath;
    ImageView imgChosen;
    TypefaceTextView btnChooseImageFile;
    TypefaceTextView btnUploadImage;
}
