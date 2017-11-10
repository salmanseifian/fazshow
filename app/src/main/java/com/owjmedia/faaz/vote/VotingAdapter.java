package com.owjmedia.faaz.vote;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.data.VotingResponse;

import java.util.List;

/**
 * Created by salman on 11/10/17.
 */

public class VotingAdapter extends RecyclerView.Adapter<VotingViewHolder> {

    public VotingAdapter(List<VotingResponse> votingList) {
        this.mVotingList = votingList;
    }

    @Override
    public VotingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.item_timeline_line_padding, parent, false);
        return new VotingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VotingViewHolder holder, int position) {
        final VotingResponse votingResponse = mVotingList.get(position);

        if (!votingResponse.isEnable()) {
            holder.mTimelineView.setMarker(ResourcesCompat.getDrawable(mContext.getResources(), R.drawable.ic_radio_button_unchecked, null));
        } else if (votingResponse.isEnable()) {
            holder.mTimelineView.setMarker(ResourcesCompat.getDrawable(mContext.getResources(), R.drawable.ic_radio_button_checked, null));
        }

        if (!votingResponse.getUpdated().isEmpty()) {
            holder.mDate.setVisibility(View.VISIBLE);
            holder.mDate.setText(votingResponse.getUpdated());
        } else
            holder.mDate.setVisibility(View.GONE);

        holder.mMessage.setText(votingResponse.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LOG", "onClick: " + votingResponse.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mVotingList.size();
    }

    public void update(List<VotingResponse> votingList) {
        mVotingList = votingList;
        notifyDataSetChanged();
    }

    private List<VotingResponse> mVotingList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
}
