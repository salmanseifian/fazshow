package com.owjmedia.faaz.vote;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.data.VotingResponse;

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
        View view = mLayoutInflater.inflate(R.layout.item_timeline_line_padding, parent, false);
        return new VoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VoteViewHolder holder, int position) {
        final VotingResponse votingResponse = mVotingList.get(position);
        holder.bind(votingResponse, listener);
//
        if (!votingResponse.getCreated().isEmpty()) {
            holder.mDate.setVisibility(View.VISIBLE);
            holder.mDate.setText("27");
        } else
            holder.mDate.setVisibility(View.GONE);

        holder.mMessage.setText(votingResponse.getTitle());
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
