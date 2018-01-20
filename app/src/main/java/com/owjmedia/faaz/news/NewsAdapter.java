package com.owjmedia.faaz.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.news.model.Result;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefaceTextView;
import com.owjmedia.faaz.general.utils.ImageHelper;

import java.util.List;

/**
 * Created by salman on 11/11/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {


    public NewsAdapter(List<Result> news, OnItemClickListener listener) {
        this.mNews = news;
        this.listener = listener;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        Result news = mNews.get(position);
        holder.bind(news, listener);
        holder.txtNewsTitle.setText(news.getTitle());
        holder.txtNewsTime.setText(news.getCreatedJalali());
        ImageHelper.getInstance(mContext).imageLoader(news.getImage(), holder.imgNews, ImageHelper.ImageType.SIMPLE);
    }


    @Override
    public int getItemCount() {
        return (mNews != null ? mNews.size() : 0);
    }

    interface OnItemClickListener {
        void onItemClick(Result newsItem, ImageView imgNews, TypefaceTextView txtNews);
    }

    public void update(List<Result> news) {
        mNews = news;
        notifyDataSetChanged();
    }

    private List<Result> mNews;
    private OnItemClickListener listener;

    private Context mContext;

}
