package com.ywc.scrapper.start;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ywc.scrapper.MainActivity;
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
                SharedPreferences prefs = getSharedPreferences("Pref", MODE_PRIVATE);
                boolean isFirstRun = prefs.getBoolean("isFirstRun",true);
                if(isFirstRun)
                {
                    System.out.println("첫번쨰 실행");
                    Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
                    startActivity(intent);

                    prefs.edit().putBoolean("isFirstRun",false).apply();
                }else{
                    System.out.println("첫번쨰 실행아님");
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }

//                MainActivity.startActivity(SplashActivity.this);

                finish();
            }
        }, 2000);
    }
}


