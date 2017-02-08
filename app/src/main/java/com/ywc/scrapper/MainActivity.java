package com.ywc.scrapper;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_account_circle_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_alarm_on_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_arrow_back_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_create_new_folder_black_24dp));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public class MainPagerAdapter extends FragmentStatePagerAdapter {

        private final int TAB_COUNT = 4;
        private final int ALL = 0;
        private final int FOLDER = 1;
        private final int NOTIFICATION = 2;
        private final int SETTINGS = 3;

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch(position) {
                case ALL:
                    AllFragment tab1 = new AllFragment();
                    return tab1;
                case FOLDER:
                    FolderFragment tab2 = new FolderFragment();
                    return tab2;
                case NOTIFICATION:
                    NotificationFragment tab3 = new NotificationFragment();
                    return tab3;
                case SETTINGS:
                    SettingsFragment tab4 = new SettingsFragment();
                    return tab4;
                default:
                    return null;

            }
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }


    }
}
