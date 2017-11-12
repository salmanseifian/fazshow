package com.owjmedia.faaz.news;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.data.Item;
import com.owjmedia.faaz.data.Result;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefacedTextView;
import com.owjmedia.faaz.votedetail.VoteDetailAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by salman on 11/11/17.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {
    public NewsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Result result, final NewsAdapter.OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(result);
            }
        });
    }

    @BindView((R.id.imgNews))
    ImageView imgNews;
    @BindView(R.id.txtNewsTitle)
    TypefacedTextView txtNewsTitle;
}
