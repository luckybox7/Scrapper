package com.ywc.scrapper.start;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ywc.scrapper.R;

/**
 * Created by Yongwon on 2017. 2. 8..
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();
                IntroActivity.startActivity(SplashActivity.this);
            }
        }, 2000);
    }


}
