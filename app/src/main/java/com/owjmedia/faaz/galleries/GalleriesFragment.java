package com.owjmedia.faaz.galleries;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.galleries.model.GalleriesResponse;
import com.owjmedia.faaz.gallerydetail.GalleryDetailFragment;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.SpaceItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by salman on 11/23/17.
 */

public class GalleriesFragment extends Fragment implements GalleriesContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.galleries_frg, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mGalleryPresenter = new GalleriesPresenter(this);

        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(16));
        mGalleriesAdapter = new GalleriesAdapter(mGalleriesResponse, new GalleriesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(GalleriesResponse galleriesResponse) {
                Fragment galleryDetail = new GalleryDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constants.KEYS.IMAGE_GALLERY, getArguments().getBoolean(Constants.KEYS.IMAGE_GALLERY));
                bundle.putString(Constants.KEYS.GALLEY_ID, String.valueOf(galleriesResponse.getId()));
                galleryDetail.setArguments(bundle);
                ActivityUtils.addFragmentToActivity(getFragmentManager(), galleryDetail, R.id.contentFrame);
            }
        });
        mRecyclerView.setAdapter(mGalleriesAdapter);


        if (getArguments().getBoolean(Constants.KEYS.IMAGE_GALLERY))
            mGalleryPresenter.getImageGalleries();
        else
            mGalleryPresenter.getVideoGalleries();
    }

    @Override
    public void setPresenter(GalleriesContract.Presenter presenter) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showGalleries(List<GalleriesResponse> galleriesResponses) {
        mGalleriesAdapter.update(galleriesResponses);
    }

    GalleriesContract.Presenter mGalleryPresenter;
    GalleriesAdapter mGalleriesAdapter;
    List<GalleriesResponse> mGalleriesResponse;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
}
