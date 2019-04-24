package com.vabasca.presentation.splash;

import com.vabasca.R;
import com.vabasca.presentation.authentication.AuthenticationActivity;
import com.vabasca.presentation.base.BaseActivity;
import com.vabasca.presentation.main.MainActivity;
import com.vabasca.utils.Constants;

import android.os.Handler;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.BindView;

public class SplashActivity extends BaseActivity implements SplashContract.View{

    @BindView(R.id.progress_splash)
    ProgressBar mProgress;

    @Inject
    SplashPresenter mSplashPresenter;

    private Handler mHandler;

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initUi() {
        getSupportActionBar().hide();
        mHandler = new Handler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSplashPresenter.attachView(this);
        showSplash();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSplashPresenter.detachView();
    }

    private void showSplash() {
        Runnable runnable = () -> mSplashPresenter.authenticate();
        mHandler.postDelayed(runnable, Constants.SPLASH_TIME);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void navigateToMainScreen() {
        start(MainActivity.class);
        finish();
    }

    @Override
    public void navigateToAuthenticationScreen() {
        start(AuthenticationActivity.class);
        finish();
    }
}
