package com.owjmedia.faaz.imagedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ImageHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by salman on 11/24/17.
 */

public class ImageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.image_frg, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        ImageHelper.getInstance(getContext()).imageLoader(getArguments().getString(Constants.KEYS.IMAGE_PATH), image, ImageHelper.ImageType.GALLERY);
    }

    @BindView(R.id.image)
    ImageView image;
}
