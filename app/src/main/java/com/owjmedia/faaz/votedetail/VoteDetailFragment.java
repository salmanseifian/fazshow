package com.owjmedia.faaz.votedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.owjmedia.faaz.R;
import com.owjmedia.faaz.data.Item;
import com.owjmedia.faaz.data.VoteDetailResponse;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.AppManager;
import com.owjmedia.faaz.general.utils.AuthenticationDialog;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefacedTextView;
import com.owjmedia.faaz.general.utils.ProgressDialog;

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
        initCarouselLayoutManager();
    }

    private void initCarouselLayoutManager() {
        CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new CenterScrollListener());
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        initAdapter();
    }

    private void initAdapter() {
        mVoteDetailAdapter = new VoteDetailAdapter(mVotingItems, new VoteDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item voteItem, LottieAnimationView lottieAnimationView) {
                if (!AppManager.isLogin(getContext()))
                    showAuthenticationDialog();
                else {
                    mVoteDetailPresenter.vote(AppManager.getString(getContext(), Constants.KEYS.TOKEN), getArguments().getString(Constants.KEYS.POLL_ID), voteItem.getId());
                    lottieAnimationView.setVisibility(View.VISIBLE);
                    lottieAnimationView.playAnimation();
                }
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
    public void showCandidates(VoteDetailResponse voteDetailResponse) {
        txtPhaseTitle.setText(voteDetailResponse.getTitle());
        mVoteDetailAdapter.update(voteDetailResponse.getItems());
    }

    @Override
    public void votedSuccessfully() {
    }

    private void showAuthenticationDialog() {
        new AuthenticationDialog(getActivity()).show();
    }


    VoteDetailContract.Presenter mVoteDetailPresenter;

    VoteDetailAdapter mVoteDetailAdapter;
    List<Item> mVotingItems = new ArrayList<>();

    ProgressDialog mProgressDialog;

    @BindView(recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.txt_phase_title)
    TypefacedTextView txtPhaseTitle;

}
