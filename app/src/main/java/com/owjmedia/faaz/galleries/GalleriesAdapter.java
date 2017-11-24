package com.owjmedia.faaz.galleries;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.galleries.model.GalleriesResponse;

import java.util.List;

/**
 * Created by salman on 11/23/17.
 */

public class GalleriesAdapter extends RecyclerView.Adapter<GalleriesViewHolder> {

    public GalleriesAdapter(List<GalleriesResponse> galleriesResponse, OnItemClickListener listener) {
        this.mGalleriesResponse = galleriesResponse;
        this.listener = listener;
    }

    @Override
    public GalleriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context mContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_gallery, parent, false);
        return new GalleriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleriesViewHolder holder, int position) {
        GalleriesResponse galleriesResponse = mGalleriesResponse.get(position);
        holder.bind(galleriesResponse, listener);
    }

    @Override
    public int getItemCount() {
        return (mGalleriesResponse != null ? mGalleriesResponse.size() : 0);
    }

    interface OnItemClickListener {
        void onItemClick(GalleriesResponse galleriesResponse);
    }

    public void update(List<GalleriesResponse> galleriesResponse) {
        this.mGalleriesResponse = galleriesResponse;
        notifyDataSetChanged();
    }

    private List<GalleriesResponse> mGalleriesResponse;
    private GalleriesAdapter.OnItemClickListener listener;

}
