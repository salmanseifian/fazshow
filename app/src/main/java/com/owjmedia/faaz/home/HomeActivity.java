package com.owjmedia.faaz.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.about.AboutActivity;
import com.owjmedia.faaz.authenticate.AuthenticateActivity;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.AppManager;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefacedTextView;
import com.owjmedia.faaz.vote.VoteActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_act);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.rl_profile)
    public void goToProfile() {
        Intent profileIntent = new Intent(HomeActivity.this, AuthenticateActivity.class);
        startActivity(profileIntent);
    }

    @OnClick(R.id.rl_vote)
    public void goToVote() {
        Intent votingIntent = new Intent(HomeActivity.this, VoteActivity.class);
        startActivity(votingIntent);
    }

    @OnClick(R.id.rl_competition)
    public void goToCompetition() {
        ActivityUtils.showToast(this, getString(R.string.soon), "emoji_wink.json");
    }

    @OnClick(R.id.rl_club)
    public void goToClub() {
        ActivityUtils.showToast(this, getString(R.string.soon), "emoji_wink.json");
    }

    @OnClick(R.id.rl_info)
    public void goToInfo() {
        Intent infoActivity = new Intent(HomeActivity.this, AboutActivity.class);
        startActivity(infoActivity);
    }

    @OnClick(R.id.rl_exit)
    public void logout() {
        AppManager.setString(this, Constants.KEYS.TOKEN, "");
        mDrawerLayout.closeDrawers();
        setupDrawer();
    }

    private void setupDrawer() {
        if (!AppManager.isLogin(this)) {
            mRlExit.setVisibility(View.INVISIBLE);
            mTxtProfile.setText(getString(R.string.login_and_register));
        } else {
            mRlExit.setVisibility(View.VISIBLE);
            mTxtProfile.setText(getString(R.string.profile));
        }
    }


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.txt_profile)
    TypefacedTextView mTxtProfile;

    @BindView(R.id.rl_exit)
    ViewGroup mRlExit;
}
