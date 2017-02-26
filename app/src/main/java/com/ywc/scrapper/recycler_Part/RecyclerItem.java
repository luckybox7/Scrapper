package com.ywc.scrapper.recycler_Part;

/**
 * Created by Yongwon on 2017. 2. 8..
 */

public class RecyclerItem {
    private int image;
    private String title;
    private String body;

    public RecyclerItem(int image, String title, String body) {
        this.image = image;
        this.title = title;
        this.body = body;
    }

    public int getImage(){
        return this.image;
    }
    public String getTitle(){
        return this.title;
    }
    public String getBody() {
        return this.body;
    }
}
