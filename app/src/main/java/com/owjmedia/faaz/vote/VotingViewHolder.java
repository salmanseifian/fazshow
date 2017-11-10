package com.owjmedia.faaz.vote;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;
import com.owjmedia.faaz.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by salman on 11/10/17.
 */

class VotingViewHolder extends RecyclerView.ViewHolder {

    public VotingViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @BindView(R.id.text_timeline_date)
    TextView mDate;
    @BindView(R.id.text_timeline_title)
    TextView mMessage;
    @BindView(R.id.time_marker)
    TimelineView mTimelineView;
}
