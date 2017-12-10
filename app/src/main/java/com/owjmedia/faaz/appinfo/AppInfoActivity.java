package com.owjmedia.faaz.appinfo;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.appinfo.model.AppInfoResponse;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.AppManager;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefacedTextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AppInfoActivity extends AppCompatActivity implements AppInfoContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appinfo_act);
        ButterKnife.bind(this);
        mAppInfoPresenter = new AppInfoPresenter(this);

        // Set up the toolbar.
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        try {
            PackageInfo packageInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            ((TypefacedTextView) findViewById(R.id.txt_version)).append(" " + packageInfo.
                    versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        mAppInfoPresenter.getAppInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showMessage(String message) {
        ActivityUtils.showToast(this, message, "emoji_wink.json");
    }

    @Override
    public void showConnectionError() {

    }

    @Override
    public void showAppInfo(AppInfoResponse appInfoResponse) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtAboutUs.setText(Html.fromHtml(appInfoResponse.getAboutUs(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            txtAboutUs.setText(Html.fromHtml(appInfoResponse.getAboutUs()));
        }
    }

    private AppInfoContract.Presenter mAppInfoPresenter;
    @BindView(R.id.txt_about_us)
    TypefacedTextView txtAboutUs;
}
