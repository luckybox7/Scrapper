package com.ywc.scrapper.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ywc.scrapper.R;

import io.realm.Realm;

/**
 * Created by Yongwon on 2017. 2. 8..
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Realm.init(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences("Pref", MODE_PRIVATE);
                boolean isFirstRun = prefs.getBoolean("isFirstRun", true);
                if (isFirstRun) {
                    Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
                    startActivity(intent);

                    prefs.edit().putBoolean("isFirstRun", false).apply();
                } else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 2000);


    }
}


