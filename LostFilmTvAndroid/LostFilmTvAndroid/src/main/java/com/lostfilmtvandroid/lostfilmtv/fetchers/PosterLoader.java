package com.lostfilmtvandroid.lostfilmtv.fetchers;

import com.lostfilmtvandroid.lostfilmtv.entities.Serial;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by veinhorn on 28.4.14.
 */
@Deprecated
public class PosterLoader extends LostFilmTvFetcher {
    public static String loadPosterUrl(Serial serial) {
        String posterUrl = "";
        try {
            Document document = Jsoup.connect(serial.getPageUrl()).get();
            Elements elements = document.getElementsByClass(SerialDescriptionFetcher.MID_CLASS);
            Element midElement = elements.get(0);
            Element serialDescriptionElement = midElement.getElementsByTag(SerialDescriptionFetcher.DIV_TAG).get(1);
            posterUrl = LOSTFILM_URL + serialDescriptionElement.getElementsByTag(SerialDescriptionFetcher.IMG_TAG).get(0).attr(SerialDescriptionFetcher.SRC_ATTRIBUTE);
        } catch(IOException e) {

        }
        return posterUrl;
    }
}
