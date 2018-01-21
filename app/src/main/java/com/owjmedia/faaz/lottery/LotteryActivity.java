package com.owjmedia.faaz.lottery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefaceEditText;
import com.owjmedia.faaz.general.utils.ProgressDialog;
import com.owjmedia.faaz.general.utils.Validator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by salman on 12/13/17.
 */

public class LotteryActivity extends AppCompatActivity implements LotteryContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lottery_act);
        ButterKnife.bind(this);
        mLotteryPresenter = new LotteryPresenter(this);
        mProgressDialog = new ProgressDialog(this);
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
        ActivityUtils.showToast(this, message, "emoji_wink.json");
    }

    @Override
    public void showConnectionError() {

    }

    @OnClick(R.id.btnContinue)
    public void consumeCode() {
        if (Validator.isStringValid(edtCode.getText().toString()))
            mLotteryPresenter.consumeLotteryCode(edtCode.getText().toString());
        else
            showMessage(getString(R.string.input_is_not_valid));
    }

    private LotteryContract.Presenter mLotteryPresenter;
    private ProgressDialog mProgressDialog;

    @BindView(R.id.edtCode)
    TypefaceEditText edtCode;
}
