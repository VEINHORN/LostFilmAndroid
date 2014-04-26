package com.lostfilmtvandroid;

/**
 * Created by veinhorn on 26.4.14.
 */
public class Serial {
    private String title;
    private String originalTitle;
    private String pageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
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
