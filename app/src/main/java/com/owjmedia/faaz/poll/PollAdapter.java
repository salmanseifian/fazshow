package com.owjmedia.faaz.poll;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.OnItemClickListener;
import com.owjmedia.faaz.poll.model.PollItem;

import java.util.List;


public class PollAdapter extends RecyclerView.Adapter<PollVH> {

    PollAdapter(List<PollItem> pollItems, OnItemClickListener listener) {
        this.mPollItems = pollItems;
        this.listener = listener;
    }

    @Override
    public PollVH onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.poll_item, parent, false);
        return new PollVH(view);

    }

    @Override
    public void onBindViewHolder(PollVH holder, int position) {
        PollItem pollItem = mPollItems.get(position);
        holder.bind(pollItem, position, listener);
        if (pollItem.isVoted())
            holder.llParent.setBackground(ResourcesCompat.getDrawable(mContext.getResources(),
                    R.drawable.checked_answer, null));
    }

    @Override
    public int getItemCount() {
        return mPollItems != null ? mPollItems.size() : 0;
    }

    public void update(List<PollItem> pollItems) {
        mPollItems = pollItems;
        notifyDataSetChanged();
    }

    private Context mContext;
    private List<PollItem> mPollItems;
    private OnItemClickListener listener;
}
