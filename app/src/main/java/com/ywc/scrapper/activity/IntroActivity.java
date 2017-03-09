package com.ywc.scrapper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.ywc.scrapper.R;
import com.ywc.scrapper.fragment.IntroFragment;

/**
 * Created by Yongwon on 2017. 2. 8..
 */

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        addSlide(IntroFragment.newInstance(R.layout.intro_slide_1));
        addSlide(IntroFragment.newInstance(R.layout.intro_slide_2));
        addSlide(IntroFragment.newInstance(R.layout.intro_slide_3));
        addSlide(IntroFragment.newInstance(R.layout.intro_slide_4));
        addSlide(IntroFragment.newInstance(R.layout.intro_slide_5));

        setFadeAnimation();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);

        finish();
        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);

        finish();
        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
