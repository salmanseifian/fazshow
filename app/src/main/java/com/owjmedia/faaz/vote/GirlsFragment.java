package com.owjmedia.faaz.vote;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.vote.model.VotingResponse;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.ProgressDialog;
import com.owjmedia.faaz.votedetail.VoteDetailFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by salman on 11/10/17.
 */

public class GirlsFragment extends Fragment implements VoteContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.voting_frg, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mVotingPresenter = new VotePresenter(this);

        mProgressDialog = new ProgressDialog(getActivity());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
        initViews();
    }

    private void initViews() {
        mVotingPresenter.getVotings("girls");
        mVotingAdapter = new VoteAdapter(mVotingResponse, new VoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(VotingResponse votingResponse) {
                Fragment fragment = new VoteDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.KEYS.POLL_ID, String.valueOf(votingResponse.getId()));
                fragment.setArguments(bundle);
                ActivityUtils.addFragmentToActivity(getActivity().getSupportFragmentManager(), fragment, R.id.contentFrame);
            }
        });
        mRecyclerView.setAdapter(mVotingAdapter);
    }

    @Override
    public void setPresenter(VoteContract.Presenter presenter) {
        mVotingPresenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active)
            mProgressDialog.show();
        else
            mProgressDialog.cancel();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showVotings(List<VotingResponse> votingResponses) {
        mVotingAdapter.update(votingResponses);
    }

    private VoteContract.Presenter mVotingPresenter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    VoteAdapter mVotingAdapter;
    List<VotingResponse> mVotingResponse = new ArrayList<>();

    ProgressDialog mProgressDialog;


}
