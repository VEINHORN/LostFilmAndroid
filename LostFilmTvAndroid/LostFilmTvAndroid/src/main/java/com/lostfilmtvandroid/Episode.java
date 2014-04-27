package com.lostfilmtvandroid;

/**
 * Created by veinhorn on 27.4.14.
 */
public class Episode extends Serial {
    private String seasonsNumber;
    private String rating; // 10 is max for example 9.5/10
    private String commentsNumber;
    private String commentsUrl;
    private String episodeDescriptionUrl;

    public Episode() {

    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(String commentsNumber) {
        this.commentsNumber = commentsNumber;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public String getEpisodeDescriptionUrl() {
        return episodeDescriptionUrl;
    }

    public void setEpisodeDescriptionUrl(String episodeDescriptionUrl) {
        this.episodeDescriptionUrl = episodeDescriptionUrl;
    }

    public String getSeasonsNumber() {
        return seasonsNumber;
    }

    public void setSeasonsNumber(String seasonsNumber) {
        this.seasonsNumber = seasonsNumber;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + seasonsNumber + "\n" + rating + "\n" + commentsNumber + "\n" + commentsUrl + "\n" + episodeDescriptionUrl;
    }
}
