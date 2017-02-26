package com.ywc.scrapper;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Float Action Button Test", Toast.LENGTH_SHORT).show();
            }
        });



        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("ALL ITEM"));
        tabLayout.addTab(tabLayout.newTab().setText("FOLDER"));
        tabLayout.addTab(tabLayout.newTab().setText("FAVORITE"));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_filter:
                Toast.makeText(getApplicationContext(), "filter", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "settings", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }










    public class MainPagerAdapter extends FragmentStatePagerAdapter {

        private final int TAB_COUNT = 3;
        private final int ALL = 0;
        private final int FOLDER = 1;
        private final int NOTIFICATION = 2;

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
