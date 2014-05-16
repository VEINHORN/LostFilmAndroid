package com.lostfilmtvandroid.torrentrss;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 11.5.14.
 */
public class EpisodesContainer {
    private List<Episode> episodes = new ArrayList<>();

    public void addEpisode(Episode episode) {
        episodes.add(episode);
    }

    public int size() {
        return episodes.size();
    }

    public Episode getEpisode(int position) {
        return episodes.get(position);
    }

    public String toString() {
        String str = "";
        for(Episode episode : episodes) {
            str += episode.toString() + "\n";
        }
        return str;
    }
}
