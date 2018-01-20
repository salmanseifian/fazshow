package com.owjmedia.faaz.galleries;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.galleries.model.GalleriesResponse;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefaceTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by salman on 11/23/17.
 */

class GalleriesViewHolder extends RecyclerView.ViewHolder {
    public GalleriesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final GalleriesResponse galleriesResponse, final GalleriesAdapter.OnItemClickListener listener) {
        txtGalleyTitle.setText(galleriesResponse.getTitle());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(galleriesResponse);
            }
        });
    }

    @BindView(R.id.txt_galley_title)
    TypefaceTextView txtGalleyTitle;
    @BindView(R.id.img_gallery)
    ImageView imgGallery;

}
