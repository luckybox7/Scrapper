package com.ywc.scrapper.helper;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Yongwon on 2017. 3. 7..
 */

public class ItemParser extends AsyncTask<Void, Void, Void> {



    @Override
    protected Void doInBackground(Void... params) {
//        try {
//            Document doc = Jsoup.connect(urlFromWeb).get();
//            Elements ogTagTitle = doc.select("meta[property=og:title]"); // 해당 라인 모두 긁어옴
//            Elements ogTagDescription = doc.select("meta[property=og:description]");
//            Elements ogTagImageURL = doc.select("meta[property=og:image]");
//
//            String title = ogTagTitle.attr("content"); // content 부분 긁어옴
//            String description = ogTagDescription.attr("content");
//            String imageURL = ogTagImageURL.attr("content");
//
//            System.out.println(title);
//            System.out.println(description);
//            System.out.println(imageURL);

//            init();
//            insertData(title, description, imageURL);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
