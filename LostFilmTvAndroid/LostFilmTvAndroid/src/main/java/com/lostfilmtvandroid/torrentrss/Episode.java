package com.lostfilmtvandroid.torrentrss;

/**
 * Created by veinhorn on 11.5.14.
 */
public class Episode {
    private String title = "";
    private String hqTorrentLink = "";
    private String torrentLink = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHqTorrentLink() {
        return hqTorrentLink;
    }

    public void setHqTorrentLink(String hqTorrentLink) {
        this.hqTorrentLink = hqTorrentLink;
    }

    public String getTorrentLink() {
        return torrentLink;
    }

    public void setTorrentLink(String torrentLink) {
        this.torrentLink = torrentLink;
    }

    @Override
    public String toString() {
        return title + "\n" + hqTorrentLink + "\n" + torrentLink;
    }
}
