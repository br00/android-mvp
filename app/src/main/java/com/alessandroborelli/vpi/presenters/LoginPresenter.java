package com.alessandroborelli.vpi.presenters;

import android.content.Context;

import com.alessandroborelli.vpi.interactors.LoginInteractor;

/**
 * Created by br00 on 25/05/2016.
 */
public class LoginPresenter implements ILoginVPI.PresenterActions, ILoginVPI.RequiredPresenterActions{

    private ILoginVPI.RequiredViewActions mView;
    private ILoginVPI.InteractorActions mInteractors;

    public LoginPresenter(ILoginVPI.RequiredViewActions aView, Context aContext){
        mView = aView;
        mInteractors = new LoginInteractor(this, aContext);
    }


    /**
     * VIEW  ->  PRESENTER
     */

    @Override public void onCreate() {

    }

    @Override public void onResume() {
        mInteractors.checkStage();
    }

    @Override public void onStop() {

    }

    @Override public void onDestroy() {

    }

    @Override public void requestAuthentication(String typedPassword) {
        mInteractors.authenticate(typedPassword);
    }

    @Override public void requestLogout() {
        mInteractors.logout();
    }

    /**
     * END
     */


    /**
     * INTERACTOR  ->  PRESENTER
     */

    @Override public void onAuthenticated() {
        mView.goToHome();
    }

    @Override public void onPasswordResetted() {

    }

    @Override public void onUpdate(LoginInteractor.Stage aStage) {
        mView.updateUI(aStage);
    }

    @Override public void onError(String errorMsg) {
        mView.showError(errorMsg);
    }

    /**
     * END
     */
}
