package com.ywc.scrapper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by Yongwon on 2017. 2. 12..
 */

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        toolbar.setTitle("설정");
        toolbar.setNavigationIcon(R.drawable.btn_title_back_white_n);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
