package com.owjmedia.faaz.gallerydetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.galleries.model.GalleriesResponse;
import com.owjmedia.faaz.gallerydetail.model.GalleryItem;
import com.owjmedia.faaz.general.utils.ImageHelper;

import java.util.List;

/**
 * Created by salman on 11/23/17.
 */

public class GalleryDetailAdapter extends RecyclerView.Adapter<GalleryDetailViewHolder> {

    public GalleryDetailAdapter(List<GalleryItem> galleryItems, OnItemClickListener listener) {
        this.mGalleryItems = galleryItems;
        this.listener = listener;
    }

    @Override
    public GalleryDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_gallery_detail, parent, false);
        return new GalleryDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryDetailViewHolder holder, int position) {
        GalleryItem galleryItem = mGalleryItems.get(position);
        holder.bind(galleryItem, listener);

        ImageHelper.getInstance(mContext).imageLoader(galleryItem.getImage(), holder.imgGalleryItem, ImageHelper.ImageType.GALLERY);
    }

    @Override
    public int getItemCount() {
        return (mGalleryItems != null ? mGalleryItems.size() : 0);
    }

    interface OnItemClickListener {
        void onItemClick(GalleryItem galleryItem);
    }

    public void update(List<GalleryItem> galleriesResponse) {
        this.mGalleryItems = galleriesResponse;
        notifyDataSetChanged();
    }

    private List<GalleryItem> mGalleryItems;
    private GalleryDetailAdapter.OnItemClickListener listener;

    Context mContext;

}
