package com.owjmedia.faaz.votedetail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefaceTextView;

import butterknife.BindView;
import butterknife.ButterKnife;


class VoteDetailViewHolder extends RecyclerView.ViewHolder {

    public VoteDetailViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final int position, final int itemId, final VoteDetailAdapter.OnItemClickListener listener,
                     final ConfirmationDialog.OnVotedListener votedListener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position, itemId, votedListener);
            }
        });
    }

    @BindView(R.id.txtCandidateName)
    TypefaceTextView txtCandidateName;

    @BindView(R.id.imgCandidate)
    ImageView imgCandidate;

    @BindView(R.id.lottieCheck)
    LottieAnimationView lottieCheck;

    @BindView(R.id.parent)
    ViewGroup parent;

}
