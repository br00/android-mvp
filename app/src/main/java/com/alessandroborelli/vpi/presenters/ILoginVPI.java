package com.alessandroborelli.vpi.presenters;

import com.alessandroborelli.vpi.interactors.LoginInteractor;

/**
 * Created by br00 on 07/06/2016.
 *
 * Groups all connection operations between VPI pattern layer:
 * View, Presenter and Interactor.
 * The idea is that you can understand all worflow only reading this class.
 *
 * Basically the code follow this worflow:
 *
 * 1. User click on the login button
 * 2. [View] -> [Presenter] -> [Interactor]
 * 3. [Interactor] -> [Presenter] -> [View]
 * 4. End of scenario
 *
 * See the decription on github to have more idea on how does it work VPI.
 *
 */

public interface ILoginVPI {

    /**
     * Presenter operations available from the View
     *      View -> Presenter
     */
    interface PresenterActions{
        void onCreate();
        void onResume();
        void onStop();
        void onDestroy();
        void requestAuthentication(String typedPassword);
        void requestLogout();
    }

    /**
     * Interactor operations available from the Presenter
     *      Presenter -> Interactor
     */
    interface InteractorActions {
        void authenticate(String typedPassword);
        void enrollNewUser();
        void resetPassword();
        void logout();
        void checkStage();
        void onDestroy();
    }

    /**
     * Presenter operations available from the Interactor
     *      Interactor -> Presenter
     */
    interface RequiredPresenterActions {
        void onAuthenticated();
        void onPasswordResetted();
        void onUpdate(LoginInteractor.Stage aStage);
        void onError(String errorMsg);
    }

    /**
     * View operations available from the Presenter
     *      Presenter -> View
     */
    interface RequiredViewActions {
        void goToHome();
        void updateUI(LoginInteractor.Stage aStage);
        void showError(String msg);
    }

}
