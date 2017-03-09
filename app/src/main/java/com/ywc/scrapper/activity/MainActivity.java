package com.ywc.scrapper.activity;

import android.app.Activity;
import android.content.Intent;
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

import com.afollestad.materialdialogs.MaterialDialog;
import com.ywc.scrapper.R;
import com.ywc.scrapper.helper.ItemParser;
import com.ywc.scrapper.fragment.ItemFragment;
import com.ywc.scrapper.fragment.FavoriteFragment;
import com.ywc.scrapper.fragment.FolderFragment;
import com.ywc.scrapper.manager.DBmanager;


public class MainActivity extends AppCompatActivity {

    String urlFromWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                Toast.makeText(getApplicationContext(), "filter", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "activity_settings", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.logo_typeface);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_item));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_folder));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_favorite));
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAddType();
            }
        });
    }

    public class MainPagerAdapter extends FragmentStatePagerAdapter {

        private final int TAB_COUNT = 3;
        private final int ITEM = 0;
        private final int FOLDER = 1;
        private final int FAVORITE = 2;

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case ITEM:
                    return new ItemFragment();
                case FOLDER:
                    return new FolderFragment();
                case FAVORITE:
                    return new FavoriteFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }
    }

    public void selectAddType() {
        new MaterialDialog.Builder(this)
                .title(R.string.choice)
                .items("폴더", "아이템")
                //// TODO: 2017. 3. 9. int형? 체크할 것
//                .items(R.string.select_folder, R.string.select_item)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        if (text.toString().equals("아이템")) {
                            addItem(); // 아아템 추가
                        } else {
                            addFolder();
                        }
                    }
                })
                .show();
    }

    public void addItem() {
        new MaterialDialog.Builder(this)
                .title(R.string.input)
                .content(R.string.add_item)
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME)
                .positiveText(R.string.submit)
                .input(R.string.input_hint, R.string.input_hint, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        urlFromWeb = input.toString();

//                        new GetHtmlOgTag().execute(null, null, null);


                        ItemParser.ResultCallback result = new ItemParser.ResultCallback(){

                            @Override
                            public void onSuccess(String title, String description, String imageURL) {
                                System.out.println("콜백 테스트");
                                DBmanager.insertItem(title, description, imageURL);
                            }

                            @Override
                            public void onFailure() {

                            }
                        };

                        new ItemParser(urlFromWeb, result);


                    }
                }).show();
    }

    public void addFolder() {
        new MaterialDialog.Builder(this)
                .title(R.string.input)
                .content(R.string.add_folder)
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME)
                .positiveText(R.string.submit)
                .input(R.string.input_hint, R.string.input_hint, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        Toast.makeText(getApplicationContext(), "입력한 폴더명은 " + input.toString() + " 입니다", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
}


