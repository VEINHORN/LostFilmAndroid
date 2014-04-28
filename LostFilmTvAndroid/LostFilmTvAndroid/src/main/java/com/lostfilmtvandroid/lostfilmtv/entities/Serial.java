package com.lostfilmtvandroid.lostfilmtv.entities;

/**
 * Created by veinhorn on 26.4.14.
 */
public class Serial extends BaseSerial {
    protected String pageUrl;

    public Serial() {

    }

    public Serial(String title, String originalTitle, String pageUrl) {
        this.title = title;
        this.originalTitle = originalTitle;
        this.pageUrl = pageUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    @Override
    public String toString() {
        return title + "\n" + originalTitle + "\n" + pageUrl;
    }
}
