package com.lostfilmtvandroid.lostfilmtv.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by veinhorn on 28.4.14.
 */
public class Seasons implements Iterable<Season> {
    private List<Season> seasons = new ArrayList<>();

    public void addSeason(Season season) {
        seasons.add(season);
    }

    public Season getSeason(int position) {
        return seasons.get(position - 1);
    }

    public int size() {
        return seasons.size();
    }

    public Iterator<Season> iterator() {
        return seasons.iterator();
    }

    public void reverse() {
        Collections.reverse(seasons);
    }
}
