package com.owjmedia.faaz.vote;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.vote.model.VotingResponse;

import java.util.List;


class VoteAdapter extends RecyclerView.Adapter<VoteViewHolder> {

    VoteAdapter(List<VotingResponse> votingList, OnItemClickListener listener) {
        this.mVotingList = votingList;
        this.listener = listener;
    }

    @Override
    public VoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.item_phase, parent, false);
        return new VoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VoteViewHolder holder, int position) {
        final VotingResponse votingResponse = mVotingList.get(position);

        holder.bind(votingResponse, listener);

        if (votingResponse.isEnable()) {
            holder.rlPhase.setBackgroundResource(R.drawable.bg_phase_enable);
        } else {
            holder.rlPhase.setBackgroundResource(R.drawable.bg_phase_disable);
        }

        holder.txtPhaseTitle.setText(votingResponse.getTitle());
        holder.txtPhaseDescription.setText(votingResponse.getDescription());
        holder.txtDate.setText(votingResponse.getCreated());
    }

    @Override
    public int getItemCount() {
        return (mVotingList != null ? mVotingList.size() : 0);
    }

    interface OnItemClickListener {
        void onItemClick(VotingResponse votingResponse);
    }

    void update(List<VotingResponse> votingList) {
        mVotingList = votingList;
        notifyDataSetChanged();
    }

    private List<VotingResponse> mVotingList;
    private OnItemClickListener listener;

    private Context mContext;

}
