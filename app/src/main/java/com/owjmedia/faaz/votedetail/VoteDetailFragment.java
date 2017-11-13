package com.owjmedia.faaz.votedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.data.Item;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.AppManager;
import com.owjmedia.faaz.general.utils.AuthenticationDialog;
import com.owjmedia.faaz.general.utils.ProgressDialog;
import com.volokh.danylo.layoutmanager.LondonEyeLayoutManager;
import com.volokh.danylo.layoutmanager.scroller.IScrollHandler;
import com.volokh.danylo.utils.DebugRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.owjmedia.faaz.R.id.recyclerView;


public class VoteDetailFragment extends Fragment implements VoteDetailContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.vote_detail_frg, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mVoteDetailPresenter = new VoteDetailPresenter(this);

        mProgressDialog = new ProgressDialog(getActivity());
        // Set up recycler view
        initViews();
    }

    private void initViews() {

        int screenWidth = getActivity().getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getActivity().getResources().getDisplayMetrics().heightPixels;

        int circleRadius = screenWidth;

        int xOrigin = -screenWidth / 2;
        int yOrigin = screenHeight / 2;
//        int xOrigin = -200;
//        int yOrigin = 0;
        mRecyclerView.setParameters(circleRadius, xOrigin, yOrigin);
//
//            // use this setting to improve performance if you know that changes
//            // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        mLondonEyeLayoutManager = new LondonEyeLayoutManager(
                circleRadius,
                xOrigin,
                yOrigin,
                mRecyclerView,
                IScrollHandler.Strategy.NATURAL);

        mRecyclerView.setLayoutManager(mLondonEyeLayoutManager);//new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

//        mRecyclerView.setLayoutManager(new CircularLayoutManager(getContext(), 200, -100));
        mVoteDetailAdapter = new VoteDetailAdapter(mVotingItems, new VoteDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item voteItem) {
                if (!AppManager.isLogin(getContext()))
                    showAuthenticationDialog();
                else
                    mVoteDetailPresenter.vote(AppManager.getString(getContext(), Constants.KEYS.TOKEN), getArguments().getString(Constants.KEYS.POLL_ID), voteItem.getId());
            }
        });

        mRecyclerView.setAdapter(mVoteDetailAdapter);

        mVoteDetailPresenter.getCandidates(getArguments().getString(Constants.KEYS.POLL_ID));
    }


    @Override
    public void setPresenter(VoteDetailContract.Presenter presenter) {

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
    public void showCandidates(List<Item> candidates) {
        mVoteDetailAdapter.update(candidates);
    }

    @Override
    public void votedSuccessfully() {
        Toast.makeText(getContext(), "Voted Successfully", Toast.LENGTH_SHORT).show();
    }

    private void showAuthenticationDialog() {
        new AuthenticationDialog(getActivity()).show();
    }


    VoteDetailContract.Presenter mVoteDetailPresenter;

    VoteDetailAdapter mVoteDetailAdapter;
    LondonEyeLayoutManager mLondonEyeLayoutManager;
    List<Item> mVotingItems = new ArrayList<>();

    @BindView(recyclerView)
    DebugRecyclerView mRecyclerView;

    ProgressDialog mProgressDialog;

}
