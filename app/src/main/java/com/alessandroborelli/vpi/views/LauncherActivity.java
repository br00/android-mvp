package com.alessandroborelli.vpi.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by aborelli on 25/05/2016.
 *
 * Starts the right activity based on your state (sign in or not).
 */
public class LauncherActivity extends Activity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
