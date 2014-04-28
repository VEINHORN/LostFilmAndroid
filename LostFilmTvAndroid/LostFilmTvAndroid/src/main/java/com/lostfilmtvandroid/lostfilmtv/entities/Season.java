package com.lostfilmtvandroid.lostfilmtv.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by veinhorn on 28.4.14.
 */
public class Season {
    private List<Episode> episodes = new ArrayList<>();
    private Integer seasonNumber;

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public void addEpisode(Episode episode) {
        episodes.add(episode);
    }

    public Episode getEpisode(int position) {
        return episodes.get(position - 1);
    }

    public int size() {
        return episodes.size();
    }

    public void reverse() {
        Collections.reverse(episodes);
    }
}
