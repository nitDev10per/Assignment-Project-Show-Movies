package com.example.letestmovies;

import android.graphics.drawable.Drawable;

public class list {
    private String title;
    private String poster;
    private String info;
    private String releseD;
    private String rating;
    private String overView;

    public list(String title, String poster, String info, String releseD, String rating, String overView) {
        this.title = title;
        this.poster = poster;
        this.info = info;
        this.releseD = releseD;
        this.rating = rating;
        this.overView = overView;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getInfo() {
        return info;
    }

    public String getReleseD() {
        return releseD;
    }

    public String getRating() {
        return rating;
    }

    public String getOverView() {
        return overView;
    }


}
