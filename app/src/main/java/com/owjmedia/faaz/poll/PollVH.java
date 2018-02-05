package com.owjmedia.faaz.poll;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.OnItemClickListener;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefaceTextView;
import com.owjmedia.faaz.news.NewsAdapter;
import com.owjmedia.faaz.news.model.Result;
import com.owjmedia.faaz.poll.model.PollItem;


class PollVH extends RecyclerView.ViewHolder {

    PollVH(View itemView) {
        super(itemView);
        txtAnswer = itemView.findViewById(R.id.txt_answer);
        llParent = itemView.findViewById(R.id.ll_parent);
    }

    public void bind(final PollItem pollItem, final int position, final OnItemClickListener<PollItem> listener) {
        txtAnswer.setText(pollItem.getText());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(pollItem, position);
            }
        });
    }

    private TypefaceTextView txtAnswer;
    ViewGroup llParent;
}
