package com.owjmedia.faaz.votedetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.owjmedia.faaz.R;
import com.owjmedia.faaz.votedetail.model.Item;
import com.owjmedia.faaz.general.utils.ImageHelper;

import java.util.List;

/**
 * Created by salman on 11/11/17.
 */

public class VoteDetailAdapter extends RecyclerView.Adapter<VoteDetailViewHolder> {

    public VoteDetailAdapter(List<Item> voteItems, OnItemClickListener listener) {
        this.mVoteItems = voteItems;
        this.listener = listener;
    }

    @Override
    public VoteDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.item_vote_carousel, parent, false);
        return new VoteDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VoteDetailViewHolder holder, int position) {
        Item votingItem = mVoteItems.get(position);
        holder.bind(votingItem, listener);
        ImageHelper.getInstance(mContext).imageLoader(votingItem.getImage(), holder.imgCandidate, ImageHelper.ImageType.AVATAR);

        holder.txtCandidateName.setText(votingItem.getText());
        if (votingItem.isVoted()) {
            holder.lottieCheck.setVisibility(View.VISIBLE);
            holder.lottieCheck.playAnimation();
        }

        new onVotedListener() {
            @Override
            public void onVoted() {
                holder.lottieCheck.setVisibility(View.VISIBLE);
                holder.lottieCheck.playAnimation();
            }
        };


    }

    @Override
    public int getItemCount() {
        return (mVoteItems != null ? mVoteItems.size() : 0);
    }

    public void update(List<Item> voteItems) {
        mVoteItems = voteItems;
        notifyDataSetChanged();
    }

    interface OnItemClickListener {
        void onItemClick(Item voteItem, LottieAnimationView lottieAnimationView, ImageView imgCandidate);
    }

    interface onVotedListener {
        void onVoted();
    }

    private List<Item> mVoteItems;
    private VoteDetailAdapter.OnItemClickListener listener;
    private Context mContext;

}
