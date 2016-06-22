package com.alessandroborelli.vpi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.alessandroborelli.vpi.views.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_toolbar, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            result = true;
        }
        return result;
    }

    public void logout(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(LoginActivity.BUNDLE_KEY_LOGOUT, true);
        startActivity(intent);
        finish();
    }
}
