package com.rxzone.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.rxzone.Activities.DashBoardActivity.HomeActivity;
import com.rxzone.Activities.LoginActivity.LoginActivity;
import com.rxzone.Util.SharedPrefsUtil;
import com.rxzone.rxzone.R;


public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String TokenId = SharedPrefsUtil.getStringPreference(getApplicationContext(), "TOKEN_ID");
                if (TokenId != null && !TokenId.isEmpty()) {
                    Intent i = new Intent(SplashScreen.this, HomeActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(i);
                }

//                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
//                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
