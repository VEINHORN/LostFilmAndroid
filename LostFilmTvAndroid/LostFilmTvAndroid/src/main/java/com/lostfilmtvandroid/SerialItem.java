package com.lostfilmtvandroid;

/**
 * Created by veinhorn on 26.4.14.
 */
public class SerialItem extends Serial {
    protected String pageUrl;

    public SerialItem() {

    }

    public SerialItem(String title, String originalTitle, String pageUrl) {
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
