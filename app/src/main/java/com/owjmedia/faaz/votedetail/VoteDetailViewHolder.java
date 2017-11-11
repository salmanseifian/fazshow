package com.owjmedia.faaz.votedetail;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.data.Item;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefacedTextView;
import com.owjmedia.faaz.general.utils.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;


class VoteDetailViewHolder extends RecyclerView.ViewHolder {

    public VoteDetailViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Item item, final VoteDetailAdapter.OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(item);
            }
        });
    }

    @BindView(R.id.txtCandidateName)
    TypefacedTextView txtCandidateName;

    @BindView(R.id.imgCandidate)
    RoundedImageView imgCandidate;

}
