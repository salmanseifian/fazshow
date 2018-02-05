package com.owjmedia.faaz.poll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Global;
import com.owjmedia.faaz.general.OnItemClickListener;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.AuthenticationDialog;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefaceTextView;
import com.owjmedia.faaz.general.utils.ProgressDialog;
import com.owjmedia.faaz.poll.model.Poll;
import com.owjmedia.faaz.poll.model.PollItem;

import java.util.ArrayList;


public class PollActivity extends AppCompatActivity implements PollContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll_act);
        mProgressDialog = new ProgressDialog(this);
        mPollPresenter = new PollPresenter(this);
        mPollAdapter = new PollAdapter(new ArrayList<PollItem>(), new OnItemClickListener() {
            @Override
            public void onItemClick(Object item, int position) {
                if (Global.isLogin()) {
                    mPollPresenter.participateInPoll(((PollItem) item).getId());
                    ((PollItem) item).setVoted(true);
                    mItemPosition = position;
                } else
                    new AuthenticationDialog().show(getSupportFragmentManager(), null);
            }
        });
        txtQuestion = findViewById(R.id.txt_question);
        RecyclerView rvAnswers = findViewById(R.id.rv_answers);
        rvAnswers.setLayoutManager(new GridLayoutManager(this, 2));
        rvAnswers.setAdapter(mPollAdapter);
        mPollPresenter.getPoll();
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
        ActivityUtils.showToast(this, message);
    }

    @Override
    public void showConnectionError() {
    }

    @Override
    public void showPoll(Poll poll) {
        txtQuestion.setText(poll.getTitle());
        mPollAdapter.update(poll.getItems());
    }

    @Override
    public void onPollVotedSuccessfully() {
        mPollAdapter.notifyItemChanged(mItemPosition);
    }

    ProgressDialog mProgressDialog;
    private PollAdapter mPollAdapter;
    private PollContract.Presenter mPollPresenter;
    private TypefaceTextView txtQuestion;
    private int mItemPosition;
}
