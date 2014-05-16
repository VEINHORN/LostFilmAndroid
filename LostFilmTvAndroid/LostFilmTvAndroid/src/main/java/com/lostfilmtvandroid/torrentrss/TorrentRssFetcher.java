package com.lostfilmtvandroid.torrentrss;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by veinhorn on 11.5.14.
 */

public class TorrentRssFetcher {
    public final static String URL = "http://torrentrss.net";

    public static SerialsContainer fetchSerials() {
        SerialsContainer serialsContainer = new SerialsContainer();
        String result = "";
        try {
            Document document = Jsoup.connect("http://torrentrss.net/serials.html").get();
            Elements liElements = document.getElementsByClass("pagination").get(0).getElementsByTag("li"); // with all references for serials
            Elements serialElements = document.getElementById("block_main").getElementsByTag("tr");

            for(Element element : serialElements) {
                if(element.getElementsByTag("td").size() == 3) { // for ads filtering
                    Serial serial = new Serial();
                    serial.setLink(URL + element.getElementsByTag("a").get(0).attr("href"));
                    serial.setTitle(element.getElementsByTag("a").get(1).ownText());
                    serialsContainer.addSerial(serial);
                }
            }

            for(int i = 1; i < liElements.size(); i++) {
                String url = liElements.get(i).getElementsByTag("a").get(0).attr("href");
                Document newDoc = Jsoup.connect(url).get();

                Elements newSerialElements = newDoc.getElementById("block_main").getElementsByTag("tr");

                for(Element element : newSerialElements) {
                    if(element.getElementsByTag("td").size() == 3) { // for ads filtering
                        Serial serial = new Serial();
                        serial.setLink(URL + element.getElementsByTag("a").get(0).attr("href"));
                        serial.setTitle(element.getElementsByTag("a").get(1).ownText());
                        serialsContainer.addSerial(serial);
                    }
                }
            }
        } catch(IOException e) {

        }

        for(Serial serial : serialsContainer) {
            result += serial.getTitle() + "\n";
        }
        return serialsContainer;
    }

    public static EpisodesContainer getEpisodes() {
        EpisodesContainer episodesContainer = new EpisodesContainer();
        try {
            Document document = Jsoup.connect(fetchSerials().searchUrl("Dexter")).get();
            Elements episodes = document.getElementById("block_main").getElementsByAttributeValue("itemprop", "episode");
            for(Element element : episodes) {
                Episode episode = new Episode();
                episode.setTitle(element.getElementsByAttributeValue("itemprop", "name").get(0).ownText());
                Elements aElements = element.getElementsByTag("a");
                if(aElements.size() == 1) {
                    episode.setTorrentLink(aElements.get(0).attr("href"));
                } else if(aElements.size() == 2) {
                    episode.setHqTorrentLink(aElements.get(0).attr("href"));
                    episode.setTorrentLink(aElements.get(1).attr("href"));
                }
                episodesContainer.addEpisode(episode);
            }
        } catch(IOException e) {

        }
        return episodesContainer;
    }
}