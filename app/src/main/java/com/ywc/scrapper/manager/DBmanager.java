package com.ywc.scrapper.manager;


import com.ywc.scrapper.model.Content;
import com.ywc.scrapper.model.Folder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Yongwon on 2017. 3. 8..
 */

public class DBmanager {

    public static void insertItem(String title, String description, String imageURL) {
        Date date = new Date(System.currentTimeMillis());

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Content content = realm.createObject(Content.class, UUID.randomUUID().toString());
        content.setTitle(title);
        content.setDescription(description);
        content.setImage(imageURL);
        content.setFavorite(false);
        content.setDate(date);
        realm.commitTransaction();

        System.out.println(content);
    }

    public static RealmResults<Content> getItem() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Content> contentList = realm.where(Content.class)
                .findAll();

        return contentList;
    }

    public static void changeFavoriteStatus(String contentID) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Content content = realm.where(Content.class).equalTo("contentID", contentID).findFirst();
        content.setFavorite(true);

        realm.commitTransaction();
    }

    public static void deleteItem(String contentID) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        Content content = realm.where(Content.class).equalTo("contentID", contentID).findFirst();
        content.deleteFromRealm();
        realm.commitTransaction();

    }


    public static void createFolder(String folderName) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Folder folder = realm.createObject(Folder.class, UUID.randomUUID().toString());
        folder.setFolderName(folderName);
        realm.commitTransaction();

        System.out.println(folder);
    }

    public static RealmResults<Folder> getFolder() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Folder> folderList = realm.where(Folder.class)
                .findAll();

        return folderList;
    }

    public static RealmResults<Content> getFavoriteList() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Content> favoriteList = realm.where(Content.class).equalTo("favorite", true).findAll();

        return favoriteList;
    }
}
