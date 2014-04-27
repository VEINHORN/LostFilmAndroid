package com.lostfilmtvandroid;

/**
 * Created by veinhorn on 27.4.14.
 */
public class Serial {
    protected String title = "";
    protected String originalTitle = "";

    public Serial() {

    }

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

    @Override
    public String toString() {
        return title + "\n" + originalTitle;
    }
}
