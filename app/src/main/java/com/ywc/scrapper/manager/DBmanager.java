package com.ywc.scrapper.manager;


import com.ywc.scrapper.model.Content;
import java.util.Date;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Yongwon on 2017. 3. 8..
 */

public class DBmanager {

    public static void insertItem(String title, String description, String imageURL) {
        Date date = new Date(System.currentTimeMillis());

        Realm realm = Realm.getDefaultInstance();
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

    public static void getItem(String title, String description, String imageURL) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Content> contentList = realm.where(Content.class)
                .findAll();
    }
}
