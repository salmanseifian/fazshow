package com.owjmedia.faaz.vote;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.vote.model.VotingResponse;

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

    @BindView(R.id.rl_phase)
    ViewGroup rlPhase;
    @BindView(R.id.txt_day)
    TextView txtDay;
    @BindView(R.id.txt_month)
    TextView txtMonth;
    @BindView(R.id.txt_phase_title)
    TextView txtPhaseTitle;
    @BindView(R.id.txt_phase_description)
    TextView txtPhaseDescription;
    @BindView(R.id.txt_time)
    TextView txtTime;

}
