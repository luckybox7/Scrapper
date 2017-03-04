package com.ywc.scrapper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

//import com.ywc.scrapper.database.ContentGroup;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ywc.scrapper.settings_all.SettingsActivity;
import com.ywc.scrapper.viewPager_Fragment.AllFragment;
import com.ywc.scrapper.viewPager_Fragment.FavoriteFragment;
import com.ywc.scrapper.viewPager_Fragment.FolderFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    String urlFromWeb;
    private Toast toast;

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.logo_typeface);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

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

//        String action = getIntent().getAction();
//        if (action.equalsIgnoreCase(Intent.ACTION_SEND) && getIntent().hasExtra(Intent.EXTRA_TEXT)) {
//            urlFromWeb = getIntent().getStringExtra(Intent.EXTRA_TEXT);
//        }
    }

    public void fabClicked(View v) {

        new MaterialDialog.Builder(this)
                .title(R.string.input)
                .content(R.string.input_content)
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME )
                .positiveText(R.string.submit)

                .input(R.string.input_hint, R.string.input_hint, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        System.out.println("입력하신 내용은 " + input + " 입니다");
                        urlFromWeb = input.toString();
                        new getHtmlOgTag().execute(null, null, null);
                    }
                }).show();
    }

    private class getHtmlOgTag extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Document doc = Jsoup.connect(urlFromWeb).get();
                Elements ogTagTitle = doc.select("meta[property=og:title]"); // 해당 라인 모두 긁어옴
                Elements ogTagDescription = doc.select("meta[property=og:description]");

                String title = ogTagTitle.attr("content"); // content 부분 긁어옴
                String description = ogTagDescription.attr("content");

                System.out.println(title);
                System.out.println(description);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
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
                Toast.makeText(getApplicationContext(), "settings_main", Toast.LENGTH_SHORT).show();
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
                    FavoriteFragment tab3 = new FavoriteFragment();
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


