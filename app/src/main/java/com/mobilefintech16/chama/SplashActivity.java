package com.mobilefintech16.chama;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        // Duration of wait
        int SPLASH_DISPLAY_LENGTH = 3000;

        final SharedPreferences[] mSharedPref = new SharedPreferences[1];

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                mSharedPref[0] = getSharedPreferences("SharedPref", MODE_PRIVATE);
                boolean isFirstTime = mSharedPref[0].getBoolean("firstTime", true);

                if (isFirstTime) {
                    // Create an Intent that will start the OnBoardingActivity.
                    Intent onBoardIntent = new Intent(SplashActivity.this, OnBoardingActivity.class);
                    SplashActivity.this.startActivity(onBoardIntent);
                    SplashActivity.this.finish();

                    SharedPreferences.Editor editor  = mSharedPref[0].edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();
                }else{
                    Intent loginIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    SplashActivity.this.startActivity(loginIntent);
                    SplashActivity.this.finish();
                }
            }

        }, SPLASH_DISPLAY_LENGTH);
    }
}