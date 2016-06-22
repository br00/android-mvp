package com.alessandroborelli.vpi.interactors;

import android.app.Activity;
import android.content.Context;

import com.alessandroborelli.vpi.R;
import com.alessandroborelli.vpi.presenters.ILoginVPI;
import com.alessandroborelli.vpi.util.AppSettings;

/**
 * Created by br00 on 25/05/2016.
 */
public class LoginInteractor implements ILoginVPI.InteractorActions{

    private Activity mActivity;
    private ILoginVPI.RequiredPresenterActions mPresenter;
    private AppSettings mAppSettings;
    private Stage mStage;

    public LoginInteractor(ILoginVPI.RequiredPresenterActions aPresenter, Context aContext){
        mPresenter = aPresenter;
        mActivity = (Activity) aContext;
        mAppSettings = new AppSettings(mActivity);
        mStage = Stage.LOGGED_OUT;
    }


    /**
     * PRESENTER  ->  INTERACTOR
     */

    @Override public void authenticate(String typedPassword) {
        if (typedPassword.equals("1234")) {
            mAppSettings.setBoolean(AppSettings.LOGGED, true);
            mPresenter.onAuthenticated();
        } else {
            mPresenter.onError(mActivity.getResources().getString(R.string.error_wrong_password));
        }
    }

    @Override public void enrollNewUser() {

    }

    @Override public void resetPassword() {

    }

    @Override public void logout() {
        mAppSettings.setBoolean(AppSettings.LOGGED, false);
        mStage = Stage.LOGGED_OUT;
    }

    @Override public void checkStage() {
        if (mAppSettings.getBoolean(AppSettings.LOGGED)) {
            mStage = Stage.LOGGED_IN;
        } else {
            mStage = Stage.LOGGED_OUT;
        }
        mPresenter.onUpdate(mStage);
    }

    @Override public void onDestroy() {

    }

    /**
     * END
     */


    /**
     * Enumeration to indicate the status of the session.
     */

    public enum Stage {
        LOGGED_IN,
        LOGGED_OUT
    }
}
