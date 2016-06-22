package com.alessandroborelli.vpi.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alessandroborelli.vpi.MainActivity;
import com.alessandroborelli.vpi.R;
import com.alessandroborelli.vpi.interactors.LoginInteractor;
import com.alessandroborelli.vpi.presenters.ILoginVPI;
import com.alessandroborelli.vpi.presenters.LoginPresenter;

/**
 * Created by br00 on 25/05/2016.
 */
public class LoginActivity extends AppCompatActivity implements ILoginVPI.RequiredViewActions, View.OnClickListener {

    public static final String BUNDLE_KEY_LOGOUT = "logout";

    private ILoginVPI.PresenterActions mPresenter;

    private EditText mPasswordET;
    private Button mLoginBT;
    private boolean mMayHaveToLogout;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {
            // Creates presenter
            mPresenter = new LoginPresenter(this, this);
            mPresenter.onCreate();

            if (getIntent().getExtras() != null) {
                mMayHaveToLogout = getIntent().getExtras().getBoolean(BUNDLE_KEY_LOGOUT);
            }
        }

        mPasswordET = (EditText) findViewById(R.id.passwordET);
        mLoginBT = (Button) findViewById(R.id.loginBT);
        if (mLoginBT != null) {
            mLoginBT.setOnClickListener(this);
        }
    }

    @Override protected void onResume() {
        super.onResume();
        if (mMayHaveToLogout) {
            mMayHaveToLogout = false;
            mPresenter.requestLogout();
        } else {
            mPresenter.onResume();
        }
    }

    /**
     * PRESENTER  ->  VIEW
     */

    @Override public void goToHome() {
        // Launches MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override public void updateUI(LoginInteractor.Stage aStage) {
        switch (aStage) {
            case LOGGED_IN:
                goToHome();
                break;
            case LOGGED_OUT:
                break;
        }
    }

    @Override public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * END
     */


    @Override public void onClick(View v) {
        if (v.getId() == mLoginBT.getId()) {
            // Tries to authenticate
            String typedPassword = mPasswordET.getText().toString();
            mPresenter.requestAuthentication(typedPassword);
        }
    }
}
