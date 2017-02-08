package com.ywc.scrapper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;

/**
 * Created by Yongwon on 2017. 2. 8..
 */

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        addSlide(IntroSlide.newInstance(R.layout.introslide_1));
        addSlide(IntroSlide.newInstance(R.layout.introslide_2));
        addSlide(IntroSlide.newInstance(R.layout.introslide_3));

        setFadeAnimation();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.

        finish();

        MainActivity.startActivity(IntroActivity.this);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.

        finish();
        MainActivity.startActivity(IntroActivity.this);
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, IntroActivity.class));
    }


}
