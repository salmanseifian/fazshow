package com.owjmedia.faaz.gallerydetail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.galleries.model.GalleriesResponse;
import com.owjmedia.faaz.gallerydetail.model.GalleryItem;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefacedTextView;
import com.owjmedia.faaz.general.utils.ImageHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by salman on 11/23/17.
 */

class GalleryDetailViewHolder extends RecyclerView.ViewHolder {
    public GalleryDetailViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final GalleryItem galleryItem, final GalleryDetailAdapter.OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(galleryItem, imgGalleryItem);
            }
        });
    }

    @BindView(R.id.img_gallery_item)
    ImageView imgGalleryItem;
    @BindView(R.id.imgPlay)
    ImageView imgPlay;
    @BindView(R.id.rl_play)
    ViewGroup rlPlay;
}
