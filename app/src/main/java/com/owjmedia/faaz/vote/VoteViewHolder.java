package com.owjmedia.faaz.vote;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.data.VotingResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by salman on 11/10/17.
 */

class VoteViewHolder extends RecyclerView.ViewHolder {

    public VoteViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final VotingResponse votingResponse, final VoteAdapter.OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(votingResponse);
            }
        });
    }

    @BindView(R.id.text_timeline_date)
    TextView mDate;
    @BindView(R.id.text_timeline_title)
    TextView mMessage;
}
