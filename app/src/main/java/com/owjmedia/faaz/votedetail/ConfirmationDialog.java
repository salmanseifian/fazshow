package com.owjmedia.faaz.votedetail;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.votedetail.model.VoteDetailResponse;

/**
 * Created by salman on 1/16/18.
 */

public class ConfirmationDialog extends DialogFragment implements VoteDetailContract.View {

    public static ConfirmationDialog getInstance(String pollId, int vote_item, int position) {
        ConfirmationDialog confirmationDialog = new ConfirmationDialog();
        Bundle bundle = new Bundle();
        bundle.putString(POLL_ID, pollId);
        bundle.putInt(VOTE_ITEM_ID, vote_item);
        bundle.putInt(POSITION, position);
        confirmationDialog.setArguments(bundle);
        return confirmationDialog;
    }

    public void setListener(OnVotedListener listener) {
        mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.confirmation_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mVoteDetailPresenter = new VoteDetailPresenter(this);
        view.findViewById(R.id.img_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        view.findViewById(R.id.img_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVoteDetailPresenter.vote(getArguments().getString(POLL_ID),
                        getArguments().getInt(VOTE_ITEM_ID));
            }
        });
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showMessage(String message) {
        ActivityUtils.showToast(getContext(), message, "emoji_wink.json");
        dismiss();
    }

    @Override
    public void showConnectionError() {

    }

    @Override
    public void showCandidates(VoteDetailResponse voteDetailResponse) {

    }

    @Override
    public void votedSuccessfully() {
        ActivityUtils.showToast(getContext(), getString(R.string.voted_successfully), "emoji_wink.json");
        mListener.onVoted(getArguments().getInt(POSITION));
        dismiss();
    }

    public interface OnVotedListener {
        void onVoted(int position);
    }

    public static final String POLL_ID = "poll_id";
    public static final String VOTE_ITEM_ID = "vote_item_id";
    public static final String POSITION = "position";
    VoteDetailContract.Presenter mVoteDetailPresenter;
    private OnVotedListener mListener;
}
