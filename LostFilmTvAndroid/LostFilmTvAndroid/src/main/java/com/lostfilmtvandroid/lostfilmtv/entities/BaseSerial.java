package com.lostfilmtvandroid.lostfilmtv.entities;

/**
 * Created by veinhorn on 27.4.14.
 */
public class BaseSerial {
    protected String title = "";
    protected String originalTitle = "";

    public BaseSerial() {

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
