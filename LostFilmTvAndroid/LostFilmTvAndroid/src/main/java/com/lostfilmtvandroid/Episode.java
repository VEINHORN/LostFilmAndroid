package com.lostfilmtvandroid;

/**
 * Created by veinhorn on 27.4.14.
 */
public class Episode extends Serial {
    private String rating;
    private String commentsNumber;

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

    @Override
    public String toString() {
        return super.toString() + "\n" + rating + "\n" + commentsNumber;
    }
}
