package com.lostfilmtvandroid.lostfilmtv.entities;

/**
 * Created by veinhorn on 28.4.14.
 */
public class SerialItem extends Serial {
    private String posterUrl;

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + posterUrl;
    }
}
