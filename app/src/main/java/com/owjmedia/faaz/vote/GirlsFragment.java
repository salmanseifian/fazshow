package com.owjmedia.faaz.vote;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.data.VotingResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by salman on 11/10/17.
 */

public class GirlsFragment extends Fragment implements VotingContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.voting_frg, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mVotingPresenter = new VotingPresenter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        initViews();
    }

    private void initViews() {
        mVotingPresenter.getVotings("girls");
        mVotingAdapter = new VotingAdapter(mVotingResponse);
        mRecyclerView.setAdapter(mVotingAdapter);
    }

    @Override
    public void setPresenter(VotingContract.Presenter presenter) {
        mVotingPresenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showResponseCode(String code) {
        Toast.makeText(getContext(), code, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showVotings(List<VotingResponse> votingResponses) {
        mVotingAdapter.update(votingResponses);
    }

    private VotingContract.Presenter mVotingPresenter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    VotingAdapter mVotingAdapter;
    List<VotingResponse> mVotingResponse = new ArrayList<>();


}
