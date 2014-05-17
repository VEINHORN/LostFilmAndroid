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

    public String[] searchTorrentLinks(String episodeTitle) {
        String links[] = new String [2];// links[0] = ""; links[1] = "";
        for(Episode episode : episodes) {
            if(episode.getTitle().toLowerCase().contains(episodeTitle.toLowerCase())) {
                links[0] = episode.getTorrentLink();
                links[1] = episode.getHqTorrentLink();
            }
        }
        return links;
    }

    public boolean isSuchEpisode(String episodeTitle) {
        boolean flag = false;
        for(Episode episode : episodes) {
            if(episode.getTitle().toLowerCase().contains(episodeTitle.toLowerCase())) {
                flag = true;
            }
        }
        return flag;
    }
}
