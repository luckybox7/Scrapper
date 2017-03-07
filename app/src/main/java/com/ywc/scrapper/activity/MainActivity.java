package com.ywc.scrapper.activity;

import android.app.Activity;
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

import com.afollestad.materialdialogs.MaterialDialog;
import com.ywc.scrapper.R;
import com.ywc.scrapper.model.Content;
import com.ywc.scrapper.fragment.ItemFragment;
import com.ywc.scrapper.fragment.FavoriteFragment;
import com.ywc.scrapper.fragment.FolderFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Date;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    String urlFromWeb;
    private Realm realm;

    //// TODO: 2017. 3. 7. 체크
    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

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
        //// TODO: 2017. 3. 7.
        tabLayout.addTab(tabLayout.newTab().setText("ITEM ITEM"));
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

    private void init() {
        Realm.init(this);
        realm = Realm.getDefaultInstance();
    }

    private void insertData(String title, String description, String imageURL) {
        Date date = new Date(System.currentTimeMillis());

        System.out.println("테스트중2");

        realm.beginTransaction();
        Content content = realm.createObject(Content.class, "1"); // 1은 contentID (PrimaryKey), 순서대로 자동증가 필요
        content.setTitle(title);
        content.setDescription(description);
        content.setImage(imageURL);
        content.setFavorite(false);
        content.setDate(date);
        realm.commitTransaction();

        System.out.println(content);
    }

    public void selectAddType() {
        new MaterialDialog.Builder(this)
                .title(R.string.choice)
                .items("폴더", "콘텐츠")
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        if (text.toString().equals("콘텐츠")) {
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
                .content(R.string.add_content)
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME)
                .positiveText(R.string.submit)
                .input(R.string.input_hint, R.string.input_hint, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        urlFromWeb = input.toString(); // 입력받은 내용을 String 변수에 넣기?? 이렇게 해도 되나 매개변수로 넘겨줘야 될 것 같은데
//                        new GetHtmlOgTag().execute(null, null, null);

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


